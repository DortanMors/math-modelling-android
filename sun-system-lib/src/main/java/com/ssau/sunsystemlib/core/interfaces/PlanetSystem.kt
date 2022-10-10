package com.ssau.sunsystemlib.core.interfaces

import com.ssau.sunsystemlib.entity.Force
import com.ssau.sunsystemlib.entity.SpaceBody
import kotlinx.coroutines.flow.Flow

interface PlanetSystem {
    val bodies: List<SpaceBody>
}