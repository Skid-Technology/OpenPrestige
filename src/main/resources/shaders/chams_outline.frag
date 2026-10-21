#version 330 core

uniform sampler2D texSampler;
uniform vec4 color;
uniform float opacity;
uniform float rimWidth;
uniform vec2 oneTexel;

in vec2 v_uv;

layout (location=0) out vec4 FragColor;

void main()
{
    float centerVal = texture(texSampler, v_uv).r;

    if (centerVal > 0.0) {
        if (rimWidth <= 0.0) {
            FragColor = vec4(color.rgb, opacity);
            return;
        }

        float maxEdgeSearch = max(1.0, rimWidth);
        float edgeDist = maxEdgeSearch;
        int edgeSamples = 8;
        for (int i = 0; i < edgeSamples; i++) {
            float angle = (6.28318530718 * float(i)) / float(edgeSamples);
            vec2 dir = vec2(cos(angle), sin(angle));
            for (float d = 1.0; d < maxEdgeSearch; d += 1.0) {
                vec2 coord = v_uv + dir * d * oneTexel;
                float t = texture(texSampler, coord).r;
                if (t < 0.5) {
                    edgeDist = min(edgeDist, d);
                    break;
                }
            }
        }

        float rimFactor = 1.0 - clamp(edgeDist / maxEdgeSearch, 0.0, 1.0);
        vec3 bodyRgb = color.rgb * (1.0 - 0.65 * rimFactor);
        FragColor = vec4(bodyRgb, opacity);
        return;
    }

    discard;
}
