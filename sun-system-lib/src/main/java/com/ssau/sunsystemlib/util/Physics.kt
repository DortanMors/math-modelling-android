package com.ssau.sunsystemlib.util

import com.ssau.sunsystemlib.core.Constants
import com.ssau.sunsystemlib.entity.SpaceBody
import kotlin.math.pow
import kotlin.math.sqrt

fun getGravity(mass1: Double, mass2: Double, distance: Vector3d): Vector3d =
    distance * (Constants.G * mass1 * mass2 / distance.abs().pow(3))

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
    (motherPlanet.coordinate - planet.coordinate).let { r ->
        sqrt(r / r.abs() * Constants.G * (motherPlanet.mass + planet.mass) / r.abs())
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
