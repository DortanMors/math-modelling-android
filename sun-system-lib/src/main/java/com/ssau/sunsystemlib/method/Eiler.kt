package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.entity.Accelerate
import com.ssau.sunsystemlib.entity.Coordinate
import com.ssau.sunsystemlib.entity.Velocity
import com.ssau.sunsystemlib.util.times
import org.jetbrains.kotlinx.multik.ndarray.operations.plus

class Eiler{

    // velocity(N)
    // accelerate(n)
    // time(delta time)
    // return V(n+1)
    override fun getVelocity(velocity: Velocity, accelerate: Accelerate, time: Long): Velocity =
        Velocity(velocity.vector + (accelerate.vector * time))

    // coordinate(N)
    // velocity(n)
    // time(delta time)
    //return X(n+1)
    override fun coordinate(coordinate: Coordinate, velocity: Velocity, time: Long): Coordinate =
        Coordinate(coordinate.vector + (velocity.vector * time))
}
