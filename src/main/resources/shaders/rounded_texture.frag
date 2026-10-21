#version 330

uniform sampler2D texSampler;
uniform vec4 roundness;
uniform int tintMode; // 0=MULTIPLY, 1=OVERLAY, 2=REPLACE, 3=ADD, 4=SCREEN

in vec2 texCoord0;
in vec2 widthHeight;
in vec4 vertexColor;
in vec2 uvNorm;
in vec2 halfSize;

layout (location=0) out vec4 fragColor;

float sdRoundBox(vec2 p, vec2 b, vec4 r) {
    r.xy = (p.x>0.0)?r.xy : r.zw;
    r.x  = (p.y>0.0)?r.x  : r.y;
    vec2 q = abs(p)-b+r.x;
    return min(max(q.x,q.y),0.0) + length(max(q,0.0)) - r.x;
}

vec3 overlayBlend(vec3 base, vec3 blend) {
    vec3 result;
    result.r = base.r < 0.5 ? (2.0 * base.r * blend.r) : (1.0 - 2.0 * (1.0 - base.r) * (1.0 - blend.r));
    result.g = base.g < 0.5 ? (2.0 * base.g * blend.g) : (1.0 - 2.0 * (1.0 - base.g) * (1.0 - blend.g));
    result.b = base.b < 0.5 ? (2.0 * base.b * blend.b) : (1.0 - 2.0 * (1.0 - base.b) * (1.0 - blend.b));
    return result;
}

vec3 screenBlend(vec3 base, vec3 blend) {
    return 1.0 - (1.0 - base) * (1.0 - blend);
}

vec3 applyTint(vec3 texColor, vec3 tintColor, int mode) {
    if (mode == 0) {
        // MULTIPLY - Standard color tinting (default)
        return texColor * tintColor;
    } else if (mode == 1) {
        // OVERLAY - Blend color over texture
        return overlayBlend(texColor, tintColor);
    } else if (mode == 2) {
        // REPLACE - Replace texture color, keep luminance influence
        float luminance = dot(texColor, vec3(0.299, 0.587, 0.114));
        return tintColor * luminance;
    } else if (mode == 3) {
        // ADD - Additive blending
        return min(texColor + tintColor, vec3(1.0));
    } else if (mode == 4) {
        // SCREEN - Screen blending (lighter result)
        return screenBlend(texColor, tintColor);
    }
    return texColor * tintColor; // Fallback to multiply
}

void main() {
    vec4 texColor = texture(texSampler, uvNorm);

    // Calculate rounded rectangle mask
    float distance = sdRoundBox(texCoord0 - halfSize, halfSize, roundness);
    float fw = fwidth(distance);
    float maskAlpha = clamp(1.0 - distance / fw, 0.0, 1.0);

    // Combine alphas
    float finalAlpha = texColor.a * vertexColor.a * maskAlpha;
    if (finalAlpha < 0.01) discard;

    // Apply color tinting based on mode
    vec3 finalColor = applyTint(texColor.rgb, vertexColor.rgb, tintMode);
    fragColor = vec4(finalColor, finalAlpha);
}