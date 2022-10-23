package com.ssau.sunsystemlib.util

import kotlin.math.sqrt

data class Vector3d(var x: Double, var y: Double, var z: Double) {

    operator fun plus(right: Vector3d): Vector3d {
        return Vector3d(x + right.x, y + right.y, z + right.z)
    }

    operator fun minus(right: Vector3d): Vector3d {
        return Vector3d(x - right.x, y - right.y, z - right.z)
    }

    operator fun plusAssign(right: Vector3d) {
        x += right.x
        y += right.y
        z += right.z
    }

    operator fun minusAssign(right: Vector3d) {
        x -= right.x
        y -= right.y
        z -= right.z
    }

    operator fun times(value: Number): Vector3d =
        Vector3d(x * value.toDouble(), y * value.toDouble(), z * value.toDouble())

    operator fun div(value: Number): Vector3d =
        Vector3d(x / value.toDouble(), y / value.toDouble(), z / value.toDouble())

    fun abs(): Double = sqrt(x * x + y + y + z * z)
}