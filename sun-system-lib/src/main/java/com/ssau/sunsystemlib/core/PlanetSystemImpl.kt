package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.entity.SpaceBody
import java.lang.StringBuilder

class PlanetSystemImpl(
    planets: List<SpaceBody>,
) : PlanetSystem {

    override val bodies: List<SpaceBody> = planets

    override fun toString(): String =
        StringBuilder("\nPlanetSystem\n").apply {
            bodies.forEach { current ->
                append("cords: ${current.coordinate})\tvelocity ${current.velocity.abs()}\n")
            }
        }.toString()
}