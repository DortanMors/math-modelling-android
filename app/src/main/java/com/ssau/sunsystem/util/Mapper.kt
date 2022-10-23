package com.ssau.sunsystem.util

import com.ssau.sunsystem.ui.model.ApproximationMethod
import com.ssau.sunsystem.ui.model.UiSpaceBody
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.method.Beeman
import com.ssau.sunsystemlib.method.Euler
import com.ssau.sunsystemlib.method.EulerCramer
import com.ssau.sunsystemlib.method.Verlet

fun SpaceBody.mapToUi() = UiSpaceBody(
    coordinate = this.coordinate,
    color = this.colorId,
)

fun ApproximationMethod.mapToScheme() =
    when (this) {
        ApproximationMethod.EULER -> Euler
        ApproximationMethod.EULER_CRAMER -> EulerCramer
        ApproximationMethod.BEEMAN -> Beeman
        ApproximationMethod.VERLET -> Verlet
    }
