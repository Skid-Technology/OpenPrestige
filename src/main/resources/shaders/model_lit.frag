#version 330 core

in vec3 v_fragPos;
in vec3 v_normal;
in vec4 v_color;
in vec2 v_uv;

layout (location=0) out vec4 FragColor;

uniform sampler2D diffuseMap;
uniform sampler2D specularMap;
uniform sampler2D normalMap;
uniform sampler2D opacityMap;

uniform bool hasDiffuseMap;
uniform bool hasSpecularMap;
uniform bool hasNormalMap;
uniform bool hasOpacityMap;

uniform vec3 materialDiffuse;
uniform vec3 materialSpecular;
uniform vec3 materialAmbient;
uniform float materialShininess;
uniform float materialOpacity;

uniform vec3 lightDir;
uniform vec3 lightColor;
uniform vec3 ambientLight;
uniform vec3 viewPos;

const float GAMMA = 2.2;
const float INV_GAMMA = 1.0 / GAMMA;

vec3 toLinear(vec3 srgb) {
    return pow(srgb, vec3(GAMMA));
}

vec3 toSRGB(vec3 linear) {
    return pow(linear, vec3(INV_GAMMA));
}

float blinnPhongSpecular(vec3 normal, vec3 lightDir, vec3 viewDir, float shininess) {
    vec3 halfDir = normalize(lightDir + viewDir);
    float spec = pow(max(dot(normal, halfDir), 0.0), shininess);
    return spec;
}

float fresnel(vec3 normal, vec3 viewDir, float power) {
    return pow(1.0 - max(dot(normal, viewDir), 0.0), power);
}

void main()
{
    vec4 diffuseColor;
    if (hasDiffuseMap) {
        diffuseColor = texture(diffuseMap, v_uv);
        diffuseColor.rgb = toLinear(diffuseColor.rgb);
    } else {
        diffuseColor = vec4(toLinear(materialDiffuse), 1.0);
    }

    diffuseColor *= v_color;

    float opacity = materialOpacity;
    if (hasOpacityMap) {
        opacity *= texture(opacityMap, v_uv).r;
    }

    if (opacity < 0.01) discard;

    vec3 normal = normalize(v_normal);

    if (hasNormalMap) {
        vec3 tangentNormal = texture(normalMap, v_uv).xyz * 2.0 - 1.0;
        vec3 Q1 = dFdx(v_fragPos);
        vec3 Q2 = dFdy(v_fragPos);
        vec2 st1 = dFdx(v_uv);
        vec2 st2 = dFdy(v_uv);

        vec3 N = normalize(normal);
        vec3 T = normalize(Q1 * st2.t - Q2 * st1.t);
        vec3 B = -normalize(cross(N, T));
        mat3 TBN = mat3(T, B, N);

        normal = normalize(TBN * tangentNormal);
    }

    vec3 viewDir = normalize(viewPos - v_fragPos);
    vec3 lightDirection = normalize(-lightDir);

    vec3 ambient = toLinear(ambientLight) * toLinear(materialAmbient) * diffuseColor.rgb;

    float diff = max(dot(normal, lightDirection), 0.0);
    vec3 diffuse = toLinear(lightColor) * diff * diffuseColor.rgb;

    float spec = blinnPhongSpecular(normal, lightDirection, viewDir, max(materialShininess, 1.0));

    vec3 specularColor = toLinear(materialSpecular);
    if (hasSpecularMap) {
        vec3 specMapColor = texture(specularMap, v_uv).rgb;
        specularColor *= toLinear(specMapColor);
    }

    float fresnelFactor = fresnel(normal, viewDir, 5.0);
    vec3 specular = toLinear(lightColor) * spec * specularColor * (1.0 + fresnelFactor * 0.5);

    float rimIntensity = pow(1.0 - max(dot(viewDir, normal), 0.0), 3.0);
    vec3 rimLight = toLinear(lightColor) * rimIntensity * 0.15 * diffuseColor.rgb;

    vec3 result = ambient + diffuse + specular + rimLight;

    result = clamp(result, 0.0, 10.0);

    result = result / (result + vec3(1.0));

    result = toSRGB(result);

    FragColor = vec4(result, opacity * diffuseColor.a);
}
