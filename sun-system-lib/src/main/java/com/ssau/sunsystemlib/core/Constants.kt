package com.ssau.sunsystemlib.core

import kotlin.math.pow

object Constants {
    val G: Double = 6.67430 * 10.0.pow(-11)
    val DEFAULT_DELAY: Long = 100
    val DEFAULT_RADIUS: Float = 10f
    val METERS_PER_PIXEL: Float = 5E8f
    val DEFAULT_TIMESTEP: Long = 60 * 60 * 24
}