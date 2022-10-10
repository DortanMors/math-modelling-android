package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.entity.Accelerate
import com.ssau.sunsystemlib.entity.Coordinate
import com.ssau.sunsystemlib.entity.Velocity
import com.ssau.sunsystemlib.util.times
import org.jetbrains.kotlinx.multik.ndarray.operations.plus

class Verle{

    // coordinate(N)
    // pre_coordinate(n)
    // accelerate(a)
    // time(delta time)
    //return X(n+1)
    override fun coordinate(coordinate: Coordinate, pre_coordinate: Coordinate, accelerate: Accelerate, time: Long): Coordinate =
            Coordinate(coordinate.vector * 2 - pre_coordinate.vector + (accelerate.vector * (time * time)))

    override fun getVelocity(post_coordinate: Coordinate, pre_coordinate: Coordinate, time: Long): Velocity =
            Velocity((post_coordinate.vector - pre_coordinate.vector) / 2*time)
}