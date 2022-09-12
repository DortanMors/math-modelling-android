package com.ssau.sunsystemlib.util

import org.jetbrains.kotlinx.multik.ndarray.data.D3Array
import org.jetbrains.kotlinx.multik.ndarray.operations.fold
import org.jetbrains.kotlinx.multik.ndarray.operations.times
import kotlin.math.pow
import kotlin.math.sqrt

operator fun D3Array<Double>.times(value: Long): D3Array<Double> =
    this * value.toDouble()

fun D3Array<Double>.abs(): Double =
    sqrt(fold(0.0) { prev: Double, new: Double -> prev + new.pow(2) })
