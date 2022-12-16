package com.ssau.sunsystemlib.util

import com.ssau.sunsystemlib.core.Constants
import com.ssau.sunsystemlib.entity.SpaceBody
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun getGravity(mass1: Double, mass2: Double, distance: Vector3d): Vector3d =
    distance / distance.abs() * (Constants.G * mass1 * mass2 / distance.abs().pow(2))

fun superposition(current: SpaceBody, allBodies: List<SpaceBody>): Vector3d {
    val newForce = Vector3d(0.0, 0.0, 0.0)
    for (planet in allBodies) {
        if (planet != current) {
            newForce += getGravity(
                mass1 = planet.mass,
                mass2 = current.mass,
                distance = planet.coordinate - current.coordinate
            )
        }
    }
    return newForce
}

fun List<SpaceBody>.orbitalize() =
    mapIndexed { index, planet ->
        if (index > 0) {
            planet.copy(velocity = calcOrbitalVelocity(this[0], planet))
        } else {
            planet
        }
    }

fun calcOrbitalVelocity(motherPlanet: SpaceBody, planet: SpaceBody): Vector3d =
    calcOrbitalVelocity(motherPlanet.coordinate, motherPlanet.mass, planet.coordinate, planet.mass)

fun calcOrbitalVelocity(motherCoordinate: Vector3d, motherMass: Double, planetCoordinate: Vector3d, planetMass: Double): Vector3d =
    (motherCoordinate - planetCoordinate).let { r ->
        sqrt(Vector3d(abs(r.x), abs(r.y), abs(r.z)) / r.abs() * Constants.G * (motherMass + planetMass) / r.abs())
    }

fun calcOrbitalVelocityScalar(motherCoordinate: Vector3d, motherMass: Double, planetCoordinate: Vector3d, planetMass: Double): Double {
    val distance = (motherCoordinate - planetCoordinate).abs()
    return if (distance < 1) {
        0.0
    } else {
        (sqrt(Constants.G * (motherMass + planetMass) / distance) * 100).roundToInt() / 100.0
    }
}

fun calcOrbitalVelocityScalar(motherPlanet: SpaceBody, planet: SpaceBody): Double {
    val r = motherPlanet.coordinate - planet.coordinate
    return planet.run {
        sqrt(Constants.G * (motherPlanet.mass + mass) / r.abs())
    }
}

fun List<SpaceBody>.orbitalizeScalar() =
    mapIndexed { index, planet ->
        if (index > 0) {
            planet.copy(velocity = Vector3d(0.0, calcOrbitalVelocityScalar(this[0], planet), 0.0))
        } else {
            planet
        }
    }
