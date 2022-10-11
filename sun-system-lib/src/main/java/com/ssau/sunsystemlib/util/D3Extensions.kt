package com.ssau.sunsystemlib.util

import com.ssau.sunsystemlib.core.Constants
import org.jetbrains.kotlinx.multik.ndarray.operations.div
import kotlin.math.pow

fun getGravity(mass1: Double, mass2: Double, radius: Vector3d): Vector3d =
    radius * (Constants.G * mass1 * mass2 / radius.abs().pow(3))
