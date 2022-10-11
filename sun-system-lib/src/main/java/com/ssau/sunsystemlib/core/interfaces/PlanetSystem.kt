package com.ssau.sunsystemlib.core.interfaces

import com.ssau.sunsystemlib.entity.SpaceBody

interface PlanetSystem {
    val bodies: List<SpaceBody>
}