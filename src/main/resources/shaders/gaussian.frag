#version 330

uniform sampler2D texSampler;
uniform vec2 direction;
uniform float width;
uniform float sigma;

in vec2 v_uv;

layout (location=0) out vec4 fragColor;

float pdf(float x, float sigma) {
    return exp(-((x*x) / (sigma*sigma)));
}

void main() {
    vec2 oneTexel = 1.0 / textureSize(texSampler, 0);
    // Clamp sample UVs to the innermost texel centers so edge samples never wrap,
    // regardless of the source texture's wrap mode (we sample Minecraft's color
    // attachment directly and must not mutate its texture parameters).
    vec2 halfTexel = 0.5 * oneTexel;

    vec2 stepVec = oneTexel * direction;
    float sum = 0.0;
    vec3 blurred = vec3(0.0);
    for (float j = -width; j <= width; j += 1.0) {
        float w = pdf(j, sigma);
        sum += w;
        vec2 uv = clamp(v_uv + stepVec * j, halfTexel, 1.0 - halfTexel);
        blurred += texture(texSampler, uv).rgb * w;
    }
    fragColor = vec4(blurred / sum, 1.0);
}