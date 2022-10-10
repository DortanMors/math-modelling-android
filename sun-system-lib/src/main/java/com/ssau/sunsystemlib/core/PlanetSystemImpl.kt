package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.entity.Force
import com.ssau.sunsystemlib.entity.SpaceBody
import kotlinx.coroutines.flow.Flow

class PlanetSystemImpl : PlanetSystem {
    override val timeStep: Long
        get() = TODO("Not yet implemented")

    override val scheme: Scheme
        get() = TODO("Not yet implemented")

    override val bodies: List<SpaceBody>
        get() = TODO("Not yet implemented")

    override val bodiesState: Flow<Map<SpaceBody, Force>>
        get() = TODO("Not yet implemented")
}