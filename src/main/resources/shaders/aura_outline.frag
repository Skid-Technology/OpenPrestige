#version 330 core

uniform sampler2D texSampler;
uniform vec4 color;
uniform int quality;
uniform float radius;
uniform float fill;
uniform float lineWidth;
uniform float haloOpacity;
uniform float coreBoost;
uniform vec2 oneTexel;

in vec2 v_uv;

layout (location=0) out vec4 FragColor;

void main()
{
    float centerVal = textureLod(texSampler, v_uv, 0.0).r;

    vec3 brightColor = min(vec3(1.0), color.rgb * 1.6 + vec3(0.12));

    if (centerVal > 0.0) {
        float maxEdgeSearch = max(2.0, radius * 0.22);
        float edgeDist = maxEdgeSearch;
        int edgeSamples = 8;
        for (int i = 0; i < edgeSamples; i++) {
            float angle = (6.28318530718 * float(i)) / float(edgeSamples);
            vec2 dir = vec2(cos(angle), sin(angle));
            for (float d = 1.0; d <= maxEdgeSearch; d += 1.0) {
                vec2 coord = v_uv + dir * d * oneTexel;
                float t = textureLod(texSampler, coord, 0.0).r;
                if (t < 0.5) {
                    edgeDist = min(edgeDist, d);
                    break;
                }
            }
        }
        float edgeFactor = 1.0 - clamp(edgeDist / maxEdgeSearch, 0.0, 1.0);
        edgeFactor = pow(edgeFactor, 0.6);
        vec3 bodyRgb = mix(color.rgb, brightColor, edgeFactor);
        float bodyAlpha = clamp(fill + edgeFactor * 0.85, 0.0, 1.0);
        FragColor = vec4(bodyRgb, bodyAlpha);
        return;
    }

    float minDist = 1e9;
    float maxSearch = max(1.0, radius);

    // Coarse mip early-out: sample 4 taps one LOD level lower than maxSearch to detect empty regions.
    float coarseLod = ceil(log2(maxSearch));
    vec2 off = maxSearch * 0.5 * oneTexel;
    float coarse = textureLod(texSampler, v_uv + vec2(-off.x, -off.y), coarseLod).r
                 + textureLod(texSampler, v_uv + vec2( off.x, -off.y), coarseLod).r
                 + textureLod(texSampler, v_uv + vec2(-off.x,  off.y), coarseLod).r
                 + textureLod(texSampler, v_uv + vec2( off.x,  off.y), coarseLod).r;
    if (coarse <= 0.0) { FragColor = vec4(0.0); return; }

    // Dense 24-angle short-range probe (d=1..4 texels). Independent of the Quality setting so the
    // crisp white core line lands reliably even at Quality 1 — the wider-angle-gap main loop below
    // used to miss silhouette pixels within 1-2 texels off-axis, which is why Q=10 was the only
    // setting that produced the solid glow before.
    const int SHORT_ANGLES = 24;
    const float SHORT_MAX = 4.0;
    for (int a = 0; a < SHORT_ANGLES; a++) {
        float ang = (6.28318530718 * float(a)) / float(SHORT_ANGLES);
        vec2 dir = vec2(cos(ang), sin(ang));
        for (float d = 1.0; d <= SHORT_MAX; d += 1.0) {
            vec2 coord = v_uv + dir * d * oneTexel;
            if (textureLod(texSampler, coord, 0.0).r > 0.0) {
                minDist = min(minDist, d);
                break;
            }
        }
    }

    // Quality-scaled long-range polar search — fills in the halo's smooth distance falloff for
    // fragments farther out. Skips per-angle work early once minDist is already at core-scale.
    int angleSteps = 6 + quality * 2;
    int distSteps = 3 + quality;

    // Hoist loop-invariant math above the angle loop.
    float stepLen = maxSearch / float(distSteps);
    vec2 stepTex = stepLen * oneTexel;
    // 2D rotation recurrence: one sin/cos pair total instead of per-ray.
    float stepAngle = 6.28318530718 / float(angleSteps);
    vec2 rot = vec2(cos(stepAngle), sin(stepAngle));
    vec2 dir = vec2(1.0, 0.0);

    for (int a = 0; a < angleSteps; a++) {
        vec2 stepVec = dir * stepTex;
        vec2 coord = v_uv;
        float dist = 0.0;
        for (int d = 1; d <= distSteps; d++) {
            coord += stepVec;
            dist += stepLen;
            if (dist >= minDist) break;
            float t = textureLod(texSampler, coord, 0.0).r;
            if (t > 0.0) {
                minDist = min(minDist, dist);
                break;
            }
        }
        dir = vec2(dir.x * rot.x - dir.y * rot.y, dir.x * rot.y + dir.y * rot.x);
    }

    if (minDist >= 1e8) {
        FragColor = vec4(0.0);
        return;
    }

    float line = smoothstep(lineWidth + 1.0, lineWidth, minDist);
    float halo = exp(-minDist / max(1.0, radius) * 3.0) * haloOpacity;
    float combinedAlpha = clamp(max(line, halo), 0.0, 1.0);

    vec3 coreColor = mix(color.rgb, vec3(1.0), coreBoost * line);
    vec3 haloColor = color.rgb;
    vec3 finalRgb = mix(haloColor, coreColor, line);

    FragColor = vec4(finalRgb, combinedAlpha);
}
