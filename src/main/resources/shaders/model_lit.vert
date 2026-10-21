#version 330 core

layout (location = 0) in vec3 a_pos;
layout (location = 1) in vec3 a_normal;
layout (location = 2) in vec4 a_col;
layout (location = 3) in vec2 a_uv;

out vec3 v_fragPos;
out vec3 v_normal;
out vec4 v_color;
out vec2 v_uv;

uniform mat4 projMat;
uniform mat4 viewMat;
uniform mat4 modelMat;

void main()
{
    vec4 worldPos = modelMat * vec4(a_pos, 1.0);
    v_fragPos = worldPos.xyz;
    gl_Position = projMat * viewMat * worldPos;

    v_normal = mat3(modelMat) * a_normal;
    v_color = a_col;
    v_uv = a_uv;
}

