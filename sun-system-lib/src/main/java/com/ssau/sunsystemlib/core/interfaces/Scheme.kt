package com.ssau.sunsystemlib.core.interfaces

import com.ssau.sunsystemlib.entity.Accelerate
import com.ssau.sunsystemlib.entity.Coordinate
import com.ssau.sunsystemlib.entity.Velocity

interface Scheme {
    fun getVelocity(velocity: Velocity, accelerate: Accelerate, time: Long): Velocity
    fun coordinate(coordinate: Coordinate, velocity: Velocity, time: Long): Coordinate
}