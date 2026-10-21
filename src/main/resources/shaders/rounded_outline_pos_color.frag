#version 330

in vec2 centeredPos;
flat in vec2 halfSize;
in vec4 vertexColor;

layout (location=0) out vec4 fragColor;

uniform vec4 roundness;
uniform float outlineThickness = 1.0;

float sdRoundBox( vec2 p, vec2 b, vec4 r )
{
    r.xy = (p.x>0.0)?r.xy : r.zw;
    r.x  = (p.y>0.0)?r.x  : r.y;
    vec2 q = abs(p)-b+r.x;
    return min(max(q.x,q.y),0.0) + length(max(q,0.0)) - r.x;
}

void main() {
    // Calculate distance to shrunken shape boundary
    float dist = sdRoundBox(centeredPos, halfSize - outlineThickness, roundness);

    float fw = fwidth(dist);

    // Simple outline: visible within outlineThickness distance of boundary
    float alpha = 1.0 - smoothstep(outlineThickness - fw, outlineThickness + fw, abs(dist));

    vec4 color = vertexColor * vec4(1.0, 1.0, 1.0, alpha);
    if (color.a == 0.0) discard;
    fragColor = color;
}