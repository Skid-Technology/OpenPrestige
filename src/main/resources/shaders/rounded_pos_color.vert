#version 330

layout(location=0) in vec3 Position;
layout(location=1) in vec2 UV0;
layout(location=2) in vec2 Size;
layout(location=3) in vec4 Color;

out vec2 texCoord0;
out vec2 widthHeight;
out vec2 centeredPos;
flat out vec2 halfSize;
out vec4 vertexColor;

uniform mat4 projMat; // 0
uniform mat4 viewMat; // 1

void main() {
    gl_Position = projMat * viewMat * vec4(Position, 1.0);

    texCoord0 = UV0;
    widthHeight = Size; // hacky shit
    halfSize = Size * 0.5;
    centeredPos = UV0 - halfSize;
    vertexColor = Color;
}