package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d

object Verlet : Scheme {
    /**
     * @return X(n+1): newState`s coordinate */
    override fun calcCoordinate(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        currentState.coordinate * 2 - prevState.coordinate + currentState.accelerate * (time * time)

    /**
     * @return V(n): currentState`s coordinate */
    override fun calcVelocity(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        (calcCoordinate(prevState, currentState, newState, time) - prevState.coordinate) / time + currentState.accelerate / time
}