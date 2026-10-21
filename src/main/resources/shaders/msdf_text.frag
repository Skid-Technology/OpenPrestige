#version 330 core

in vec4 v_color;
in vec2 v_uv;

layout(location = 0) out vec4 FragColor;

uniform sampler2D msdfTexture;
uniform vec2 unitRange; // = pxRange / vec2(atlasWidth, atlasHeight)

float median(float r, float g, float b) {
    return max(min(r, g), min(max(r, g), b));
}

float screenPxRange() {
    return max(0.5 * dot(unitRange, 1.0 / fwidth(v_uv)), 1.0);
}

void main() {
    vec3 msd = texture(msdfTexture, v_uv).rgb;
    float sd = median(msd.r, msd.g, msd.b);
    float screenPxDist = screenPxRange() * (sd - 0.5);
    float opacity = clamp(screenPxDist + 0.5, 0.0, 1.0);

    FragColor = vec4(v_color.rgb, v_color.a * opacity);
}