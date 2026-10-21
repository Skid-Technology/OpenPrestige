package dev.zprestige.prestige.data;

import net.minecraft.client.render.Camera;
import org.joml.Matrix4f;

public record MatrixCameraData(Matrix4f bA, Matrix4f bB, Camera bC) {
}
