#version 330

uniform sampler2D texSampler;
uniform float glowSize;
uniform float glowStrength;
uniform vec4 glowColor;
uniform vec4 rect;
uniform vec4 roundness;
uniform vec2 screenSize;

in vec2 texCoord0;
in vec2 widthHeight;

layout (location=0) out vec4 fragColor;

float sdRoundBox(vec2 p, vec2 b, vec4 r) {
    r.xy = (p.x > 0.0) ? r.xy : r.zw;
    r.x  = (p.y > 0.0) ? r.x  : r.y;
    vec2 q = abs(p) - b + r.x;
    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r.x;
}

void main() {
    vec2 pixelPos = gl_FragCoord.xy;
    pixelPos.y = screenSize.y - pixelPos.y;

    vec2 rectCenter = rect.xy + rect.zw * 0.5;
    vec2 rectHalfSize = rect.zw * 0.5;
    vec2 localPos = pixelPos - rectCenter;

    float d = sdRoundBox(localPos, rectHalfSize, roundness);

    // Discard everything inside the rectangle (no outline/edge)
    if (d < 0.0) {
        discard;
    }

    // Only render the glow outside the shape
    float glow = 0.0;
    if (glowSize > 0.0 && d >= 0.0) {
        float t = clamp(1.0 - d / glowSize, 0.0, 1.0);
        glow = pow(t, 1.5) * glowStrength;
    }

    float a = glowColor.a * glow;

    // Discard if no glow
    if (a < 0.001) discard;

    fragColor = vec4(glowColor.rgb, a);
}