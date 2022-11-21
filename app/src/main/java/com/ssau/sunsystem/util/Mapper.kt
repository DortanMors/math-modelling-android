package com.ssau.sunsystem.util

import android.content.Context
import com.ssau.sunsystem.Defaults.METERS_PER_PIXEL
import com.ssau.sunsystem.ui.model.ApproximationMethod
import com.ssau.sunsystem.ui.model.UiSpaceBody
import com.ssau.sunsystem.ui.screen.planets.CustomizedPlanet
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.method.Beeman
import com.ssau.sunsystemlib.method.DifferenceScheme
import com.ssau.sunsystemlib.method.Euler
import com.ssau.sunsystemlib.method.EulerCramer
import com.ssau.sunsystemlib.method.Verlet
import com.ssau.sunsystemlib.util.Vector3d

fun CustomizedPlanet.toSpaceBody() = SpaceBody(
    name = name,
    mass = mass,
    coordinate = Vector3d(x, y, z),
    velocity = Vector3d(velocityX, velocityY, velocityZ),
    externalForce = Vector3d(0.0, 0.0, 0.0),
    colorId = color,
)

fun SpaceBody.mapToUi(context: Context) = UiSpaceBody(
    name = name,
    x = this.coordinate.x.mapToUiX(context),
    y = this.coordinate.y.mapToUiY(context),
    color = this.colorId,
    physic = this,
)

fun ApproximationMethod.mapToScheme() =
    when (this) {
        ApproximationMethod.EULER -> Euler
        ApproximationMethod.EULER_CRAMER -> EulerCramer
        ApproximationMethod.BEEMAN -> Beeman
        ApproximationMethod.VERLET -> Verlet
    }

val DifferenceScheme.nameId: Int
    get() = when (this) {
        Euler -> com.ssau.sunsystem.R.string.euler
        EulerCramer -> com.ssau.sunsystem.R.string.euler_cramer
        Beeman -> com.ssau.sunsystem.R.string.beeman
        Verlet -> com.ssau.sunsystem.R.string.verlet
    }

val Context.centerX: Float
    get() = resources.displayMetrics.widthPixels / 2f

val Context.centerY: Float
    get() = resources.displayMetrics.heightPixels / 2f

fun Number.mapToUiX(context: Context) = context.centerX + this.toFloat() / METERS_PER_PIXEL

fun Number.mapToUiY(context: Context) = context.centerY + this.toFloat() / METERS_PER_PIXEL
