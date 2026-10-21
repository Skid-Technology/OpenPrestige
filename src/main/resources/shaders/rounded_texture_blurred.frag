#version 330

uniform sampler2D texSampler;
uniform vec4 roundness;
uniform vec2 invBlurTexSize;

in vec2 texCoord0;
in vec2 widthHeight;
in vec2 halfSize;
in vec2 centeredPos;

layout (location=0) out vec4 fragColor;

float sdRoundBox(vec2 p, vec2 b, vec4 r) {
    r.xy = (p.x > 0.0) ? r.xy : r.zw;
    r.x  = (p.y > 0.0) ? r.x  : r.y;
    vec2 q = abs(p) - b + r.x;
    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r.x;
}

void main() {
    vec2 uv = gl_FragCoord.xy * invBlurTexSize;
    vec4 texColor = texture(texSampler, uv);

    float distance = sdRoundBox(centeredPos, halfSize, roundness);
    float alpha = clamp(1.0 - distance / fwidth(distance), 0.0, 1.0);

    if (alpha < 0.001) discard;

    fragColor = vec4(texColor.rgb, texColor.a * alpha);
}