package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.entity.SpaceBody

class PlanetSystemImpl(
    planets: List<SpaceBody>,
) : PlanetSystem {

    override val bodies: List<SpaceBody> = planets

}