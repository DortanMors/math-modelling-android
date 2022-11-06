package com.ssau.sunsystem.util

import kotlin.math.PI


fun Float.toDegrees(): Float =
    this / PI.toFloat() * 180

fun Double.formatDouble(): String = String.format("%.2e", this)
