#version 330 core

uniform sampler2D texSampler;
uniform int quality;
uniform float radius;
uniform float fill;
uniform vec2 oneTexel;

in vec2 v_uv;

layout (location=0) out vec4 FragColor;

void main()
{
    if (textureLod(texSampler, v_uv, 4.0).a <= 0.0) {
        FragColor = vec4(0.0);
        return;
    }

    vec4 center = texture(texSampler, v_uv);

    if (center.a > 0.0) {
        float maxEdgeSearch = max(2.0, radius * 0.22);
        float edgeDist = maxEdgeSearch;
        int edgeSamples = 8;
        for (int i = 0; i < edgeSamples; i++) {
            float angle = (6.28318530718 * float(i)) / float(edgeSamples);
            vec2 dir = vec2(cos(angle), sin(angle));
            for (float d = 1.0; d <= maxEdgeSearch; d += 1.0) {
                float t = texture(texSampler, v_uv + dir * d * oneTexel).a;
                if (t < 0.5) {
                    edgeDist = min(edgeDist, d);
                    break;
                }
            }
        }
        float edgeFactor = sqrt(1.0 - edgeDist / maxEdgeSearch);
        vec3 brightColor = min(vec3(1.0), center.rgb * 1.6 + vec3(0.12));
        vec3 bodyRgb = mix(center.rgb, brightColor, edgeFactor);
        float bodyAlpha = clamp(fill + edgeFactor * 0.85, 0.0, 1.0);
        FragColor = vec4(bodyRgb, bodyAlpha);
        return;
    }

    vec3 brightAccum = vec3(0.0);
    vec3 shadowAccum = vec3(0.0);
    float brightWeight = 0.0;
    float shadowWeight = 0.0;

    float effRadius = radius * 0.65;
    float innerEnd = max(1.0, effRadius * 0.4);
    float shadowEnd = effRadius;

    int angleSteps = 6 + quality * 2;
    int distSteps = 3 + quality;

    float sampleDist[8]; float bw[8]; float sw[8];
    for (int d = 1; d <= distSteps; d++) {
        float dist = shadowEnd * float(d) / float(distSteps);
        sampleDist[d] = dist;
        bool inner = dist <= innerEnd;
        float bx = 1.0 - (dist / innerEnd);
        bw[d] = inner ? bx * sqrt(bx) : 0.0;
        float sx = max(0.0, 1.0 - ((dist - innerEnd) / (shadowEnd - innerEnd)));
        sw[d] = inner ? 0.0 : sx * sx;
    }

    float stepAngle = 6.28318530718 / float(angleSteps);
    vec2 rot = vec2(cos(stepAngle), sin(stepAngle));
    vec2 dir = vec2(1.0, 0.0);
    for (int a = 0; a < angleSteps; a++) {
        for (int d = 1; d <= distSteps; d++) {
            vec4 t = texture(texSampler, v_uv + dir * sampleDist[d] * oneTexel);
            if (t.a > 0.0) {
                brightAccum += t.rgb * bw[d];
                brightWeight += bw[d];
                shadowAccum += t.rgb * sw[d];
                shadowWeight += sw[d];
            }
        }
        dir = vec2(dir.x * rot.x - dir.y * rot.y, dir.x * rot.y + dir.y * rot.x);
    }

    int totalSamples = angleSteps * distSteps;
    float bAlpha = clamp(brightWeight / float(totalSamples) * 14.0, 0.0, 1.0);
    float sAlpha = clamp(shadowWeight / float(totalSamples) * 14.9, 0.0, 1.0);

    if (bAlpha > 0.001 || sAlpha > 0.001) {
        vec3 brightCol = brightWeight > 0.0001 ? brightAccum / brightWeight : vec3(0.0);
        brightCol = min(vec3(1.0), brightCol * 1.6 + vec3(0.12));

        vec3 shadowCol = shadowWeight > 0.0001 ? (shadowAccum / shadowWeight) * 0.18 : vec3(0.0);

        vec3 outRgb = mix(shadowCol, brightCol, bAlpha);
        float outA = clamp(max(bAlpha, sAlpha * 0.7), 0.0, 1.0);
        FragColor = vec4(outRgb, outA);
        return;
    }

    FragColor = vec4(0.0);
}
