#version 330 core

uniform sampler2D texSampler;

in vec4 v_color;
in vec2 v_uv;

layout (location=0) out vec4 FragColor;

void main()
{
    vec4 col = texture(texSampler, v_uv);
    FragColor = v_color * col.a;
}