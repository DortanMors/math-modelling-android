package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d

object Euler : DifferenceScheme() {
    /**
     * @return X(n+1): newState`s coordinate */
    override fun calcCoordinate(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        currentState.coordinate + calcVelocity(prevState, currentState, newState, time) * time

    /**
     * @return V(n+1): newState`s velocity */
    override fun calcVelocity(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        currentState.velocity + newState.accelerate * time
}
