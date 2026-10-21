#version 330 core

layout (location = 0) in vec3 a_pos;
layout (location = 1) in vec4 a_col;
layout (location = 2) in vec2 a_uv;

out vec4 v_color;
out vec2 v_uv;

uniform mat4 projMat; // 0
uniform mat4 viewMat; // 1

void main()
{
    gl_Position = projMat * viewMat * vec4(a_pos, 1.0);

    v_color = a_col;
    v_uv = a_uv;
}