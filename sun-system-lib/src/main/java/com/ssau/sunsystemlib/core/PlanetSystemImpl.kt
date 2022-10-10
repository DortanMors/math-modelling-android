package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.entity.Accelerate
import com.ssau.sunsystemlib.entity.Force
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.entity.Velocity
import kotlinx.coroutines.flow.Flow

class PlanetSystemImpl(
    planets: List<SpaceBody>,
) : PlanetSystem {

    override val bodies: List<SpaceBody> = planets

}