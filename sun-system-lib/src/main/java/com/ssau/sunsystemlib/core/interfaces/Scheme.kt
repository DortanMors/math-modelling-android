package com.ssau.sunsystemlib.core.interfaces

import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d

interface Scheme {
    fun calcCoordinate(prevState: SpaceBody, currentState: SpaceBody, newState: SpaceBody, time: Long): Vector3d

    fun calcVelocity(prevState: SpaceBody, currentState: SpaceBody, newState: SpaceBody, time: Long): Vector3d
}