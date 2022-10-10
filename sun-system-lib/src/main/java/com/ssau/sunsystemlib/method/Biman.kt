package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.entity.Accelerate
import com.ssau.sunsystemlib.entity.Coordinate
import com.ssau.sunsystemlib.entity.Velocity
import com.ssau.sunsystemlib.util.times
import org.jetbrains.kotlinx.multik.ndarray.operations.plus

class Biman{

    //return X(n+1)
    override fun coordinate(coordinate: Coordinate, velocity: Velocity, pre_accelerate: Accelerate, accelerate: Accelerate, time: Long): Coordinate =
            Coordinate(coordinate.vector + velocity.vector * time +
                    (1/6)*((accelerate.vector * 4 - pre_accelerate.vector) * (time * time)))

    override fun getVelocity(velocity: Velocity, pre_accelerate: Accelerate, accelerate: Accelerate, post_accelerate: Accelerate, time: Long): Velocity =
            Velocity((velocity.vector + (1/6) * (post_accelerate.vector * 2 + accelerate.vector * 5 - pre_accelerate) * time)
}