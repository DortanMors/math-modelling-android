package com.ssau.sunsystemlib.entity

import com.ssau.sunsystemlib.core.Constants
import com.ssau.sunsystemlib.util.abs
import org.jetbrains.kotlinx.multik.ndarray.data.D3Array
import org.jetbrains.kotlinx.multik.ndarray.operations.div
import org.jetbrains.kotlinx.multik.ndarray.operations.times
import kotlin.math.pow

data class Force(val vector: D3Array<Double>) {
    companion object {
        fun getGravity(mass1: Double, mass2: Double, radius: Coordinate): Force =
            Force(radius.vector * (Constants.G * mass1 * mass2 / radius.vector.abs().pow(3)))

    }

    fun getAccelerate(mass: Double): Accelerate = Accelerate(vector / mass)
}
