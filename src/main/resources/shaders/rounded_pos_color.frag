#version 330

in vec2 centeredPos;
flat in vec2 halfSize;
in vec4 vertexColor;

layout (location=0) out vec4 fragColor;

uniform vec4 roundness;

float sdRoundBox( vec2 p, vec2 b, vec4 r )
{
    r.xy = (p.x>0.0)?r.xy : r.zw;
    r.x  = (p.y>0.0)?r.x  : r.y;
    vec2 q = abs(p)-b+r.x;
    return min(max(q.x,q.y),0.0) + length(max(q,0.0)) - r.x;
}


void main() {
    float distance = sdRoundBox(centeredPos, halfSize, roundness);
    float alpha = clamp(-distance / fwidth(distance) + 1.0, 0.0, 1.0);
    if (alpha <= 0.0) discard;
    fragColor = vec4(vertexColor.rgb, vertexColor.a * alpha);
}