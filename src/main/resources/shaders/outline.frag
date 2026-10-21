#version 330 core

uniform sampler2D texSampler;
uniform vec2 oneTexel;
uniform vec4 color;
uniform int quality;
uniform float radius;
uniform float fill;

in vec2 v_uv;

layout (location=0) out vec4 FragColor;

void main()
{
    float centerVal = texture(texSampler, v_uv).r;

    vec3 brightColor = min(vec3(1.0), color.rgb * 1.6 + vec3(0.12));
    vec3 shadowColor = color.rgb * 0.18;

    if (centerVal > 0.0) {
        float maxEdgeSearch = max(2.0, radius * 0.22);
        float edgeDist = maxEdgeSearch;
        int edgeSamples = 8;
        for (int i = 0; i < edgeSamples; i++) {
            float angle = (6.28318530718 * float(i)) / float(edgeSamples);
            vec2 dir = vec2(cos(angle), sin(angle));
            for (float d = 1.0; d <= maxEdgeSearch; d += 1.0) {
                vec2 coord = v_uv + dir * d * oneTexel;
                float t = texture(texSampler, coord).r;
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

    float brightGlow = 0.0;
    float shadowGlow = 0.0;
    float effRadius = radius * 0.65;
    float innerEnd = max(1.0, effRadius * 0.4);
    float shadowEnd = effRadius;

    int angleSteps = 6 + quality * 2;
    int distSteps = 3 + quality;

    float stepAngle = 6.28318530718 / float(angleSteps);
    float cs = cos(stepAngle);
    float sn = sin(stepAngle);
    vec2 dir = vec2(1.0, 0.0);

    float dists[16];
    float brightW[16];
    float shadowW[16];
    for (int d = 1; d <= distSteps; d++) {
        float dist = shadowEnd * (float(d) / float(distSteps));
        dists[d - 1] = dist;
        if (dist <= innerEnd) {
            brightW[d - 1] = pow(1.0 - (dist / innerEnd), 1.4);
            shadowW[d - 1] = 0.0;
        } else {
            brightW[d - 1] = 0.0;
            float f = 1.0 - ((dist - innerEnd) / (shadowEnd - innerEnd));
            shadowW[d - 1] = pow(max(0.0, f), 2.2);
        }
    }

    for (int a = 0; a < angleSteps; a++) {
        vec2 dirTexel = dir * oneTexel;
        for (int d = 0; d < distSteps; d++) {
            float t = texture(texSampler, v_uv + dirTexel * dists[d]).r;
            if (t > 0.0) {
                brightGlow += brightW[d];
                shadowGlow += shadowW[d];
            }
        }
        dir = vec2(dir.x * cs - dir.y * sn, dir.x * sn + dir.y * cs);
    }

    int totalSamples = angleSteps * distSteps;
    brightGlow = clamp(brightGlow / float(totalSamples) * 14.0, 0.0, 1.0);
    shadowGlow = clamp(shadowGlow / float(totalSamples) * 9.0, 0.0, 1.0);

    if (brightGlow > 0.001 || shadowGlow > 0.001) {
        vec3 outRgb = mix(shadowColor, brightColor, brightGlow);
        float outA = clamp(max(brightGlow, shadowGlow * 0.7), 0.0, 1.0);
        FragColor = vec4(outRgb, outA);
        return;
    }

    discard;
}
