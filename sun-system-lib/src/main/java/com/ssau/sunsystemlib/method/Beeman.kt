package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d

object Beeman : DifferenceScheme() {
    /**
     * @return X(n+1): newState`s coordinate */
    override fun calcCoordinate(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        currentState.coordinate + currentState.velocity * time + (newState.accelerate * 4 - newState.accelerate) * (time * time / 6)

    /**
     * @return V(n+1): newState`s velocity */
    override fun calcVelocity(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        currentState.velocity + (newState.accelerate * 2 + newState.accelerate * 5 - newState.accelerate) * (time / 6)
}