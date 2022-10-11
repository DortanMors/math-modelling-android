package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.entity.SpaceBody
import org.jetbrains.kotlinx.multik.ndarray.data.get

class PlanetSystemImpl(
    planets: List<SpaceBody>,
) : PlanetSystem {

    override val bodies: List<SpaceBody> = planets

    override fun toString(): String {
        return bodies.fold("\nPlanetSystem\n") { prev, current ->
            prev + "(cords: ${current.coordinate.vector[0]}, ${current.coordinate.vector[1]}, ${current.coordinate.vector[2]})\tvelocity ${current.velocity.abs()}\n"
        }
    }
}