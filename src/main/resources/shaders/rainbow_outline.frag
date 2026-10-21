#version 330 core

uniform sampler2D texSampler;
uniform float time;          // animation time (seconds)
uniform float rainbowSpeed;  // speed multiplier
uniform float saturation;    // 0–1
uniform float brightness;    // 0–1
uniform float size;          // thickness / glow radius
uniform vec2 oneTexel;       // set per pass in RainbowOutlineShader.applyUniforms(): .set2f("oneTexel", 1f/w, 1f/h)

in vec2 v_uv;

layout (location=0) out vec4 FragColor;

vec3 rgb2hsv(vec3 c)
{
    vec4 K = vec4(0.0, -1.0/3.0, 2.0/3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    float e = 1e-10;
    return vec3(abs(q.z + (q.w - q.y)/(6.0 * d + e)), d / (q.x + e), q.x);
}

vec3 hsv2rgb(vec3 c)
{
    vec4 K = vec4(1.0, 2.0/3.0, 1.0/3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - 1.0, 0.0, 1.0), c.y);
}

vec3 rainbowColor(vec2 uv)
{
    float hue = fract((uv.x + uv.y) * 0.5 + time * rainbowSpeed);
    return hsv2rgb(vec3(hue, saturation, brightness));
}

void main()
{
    float centerVal = texture(texSampler, v_uv).r;

    if (centerVal > 0.0)
    {
        FragColor = vec4(0.0);
        return;
    }

    bool isOutline = false;
    vec2 offsets[4] = vec2[4](
    vec2(1, 0), vec2(-1, 0),
    vec2(0, 1), vec2(0, -1)
    );

    for (int i = 0; i < 4; i++)
    {
        vec2 coord = v_uv + offsets[i] * oneTexel;
        if (texture(texSampler, coord).r > 0.0)
        {
            isOutline = true;
            break;
        }
    }

    vec3 rainbow = rainbowColor(gl_FragCoord.xy * 0.003);

    if (isOutline)
    {
        FragColor = vec4(rainbow, 1.0);
        return;
    }

    // Check for glow using circular sampling
    const float radius = 14.0;
    const int angleSteps = 20;
    const vec2 dirs[20] = vec2[20](
        vec2( 1.0,         0.0       ), vec2( 0.95105652,  0.30901699), vec2( 0.80901699,  0.58778525),
        vec2( 0.58778525,  0.80901699), vec2( 0.30901699,  0.95105652), vec2( 0.0,         1.0       ),
        vec2(-0.30901699,  0.95105652), vec2(-0.58778525,  0.80901699), vec2(-0.80901699,  0.58778525),
        vec2(-0.95105652,  0.30901699), vec2(-1.0,         0.0       ), vec2(-0.95105652, -0.30901699),
        vec2(-0.80901699, -0.58778525), vec2(-0.58778525, -0.80901699), vec2(-0.30901699, -0.95105652),
        vec2( 0.0,        -1.0       ), vec2( 0.30901699, -0.95105652), vec2( 0.58778525, -0.80901699),
        vec2( 0.80901699, -0.58778525), vec2( 0.95105652, -0.30901699)
    );
    const float w1 = 2.0528000; // pow(4/3, 2.5)
    const float w2 = 0.3628874; // pow(2/3, 2.5)

    float alpha = 0.0;
    for (int a = 0; a < angleSteps; a++) {
        vec2 dirTexel = dirs[a] * oneTexel;
        if (texture(texSampler, v_uv + dirTexel * (radius * (1.0 / 3.0))).r > 0.0) alpha += w1;
        if (texture(texSampler, v_uv + dirTexel * (radius * (2.0 / 3.0))).r > 0.0) alpha += w2;
    }
    if (alpha > 0.0) {
        float glowIntensity = min(alpha * (1.0 / 60.0), 1.0);
        FragColor = vec4(rainbow, glowIntensity * 0.7);
        return;
    }

    FragColor = vec4(0.0);
}