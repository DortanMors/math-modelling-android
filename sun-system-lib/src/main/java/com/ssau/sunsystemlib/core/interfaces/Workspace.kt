package com.ssau.sunsystemlib.core.interfaces

import com.ssau.sunsystemlib.entity.Force
import com.ssau.sunsystemlib.entity.SpaceBody

interface Workspace {
    val timeStep: Long
    val planetSystem: PlanetSystem
    val bodiesState: Flow<Map<PlanetSystem, Force>>
    fun start()
    fun pause()
}