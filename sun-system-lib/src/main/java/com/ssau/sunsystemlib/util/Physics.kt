package com.ssau.sunsystemlib.util

import com.ssau.sunsystemlib.core.Constants
import com.ssau.sunsystemlib.entity.SpaceBody
import kotlin.math.pow

fun getGravity(mass1: Double, mass2: Double, radius: Vector3d): Vector3d =
    radius * (Constants.G * mass1 * mass2 / radius.abs().pow(3))

fun superposition(current: SpaceBody, allBodies: List<SpaceBody>): Vector3d {
    val newForce = Vector3d(0.0, 0.0, 0.0)
    for (planet in allBodies) {
        if (planet != current) {
            newForce += getGravity(
                mass1 = planet.mass,
                mass2 = current.mass,
                radius = planet.coordinate - current.coordinate
            )
        }
    }
    return newForce
}