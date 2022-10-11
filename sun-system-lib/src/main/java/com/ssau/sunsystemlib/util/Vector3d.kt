package com.ssau.sunsystemlib.util

import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarrayOf
import org.jetbrains.kotlinx.multik.ndarray.data.D1Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.*
import kotlin.math.pow
import kotlin.math.sqrt

class Vector3d(x: Double, y: Double, z: Double) {
    constructor(d1Array: D1Array<Double>): this(d1Array[0], d1Array[1], d1Array[2]) {
        require(d1Array.size == 3) { "It is not 3d vector!" }
    }

    val vector: D1Array<Double> = mk.ndarrayOf(x, y, z)

    operator fun plus(right: Vector3d): Vector3d {
        return Vector3d(vector + right.vector)
    }

    operator fun minus(right: Vector3d): Vector3d {
        return Vector3d(vector - right.vector)
    }

    operator fun plusAssign(right: Vector3d) {
        vector += right.vector
    }

    operator fun minusAssign(right: Vector3d) {
        vector -= right.vector
    }

    operator fun times(value: Number): Vector3d =
        Vector3d(this.vector * value.toDouble())

    operator fun div(value: Number): Vector3d =
        Vector3d(this.vector / value.toDouble())

    fun abs(): Double =
        sqrt(vector.fold(0.0) { prev: Double, new: Double -> prev + new.pow(2) })
}