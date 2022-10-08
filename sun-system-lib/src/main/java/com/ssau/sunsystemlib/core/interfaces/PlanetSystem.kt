package com.ssau.sunsystemlib.core.interfaces

import com.ssau.sunsystemlib.entity.Force
import com.ssau.sunsystemlib.entity.SpaceBody
import kotlinx.coroutines.flow.Flow

interface PlanetSystem {
    val timeStep: Long
    val scheme: Scheme
    val bodies: List<SpaceBody>
    val bodiesState: Flow<Map<SpaceBody, Force>>
}