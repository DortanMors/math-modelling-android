package com.ssau.sunsystemlib.core.interfaces

import kotlinx.coroutines.flow.Flow

interface Workspace {
    val timeStep: Long
    val planetSystem: PlanetSystem
    val bodiesState: Flow<PlanetSystem>
    fun start()
    fun pause()
}