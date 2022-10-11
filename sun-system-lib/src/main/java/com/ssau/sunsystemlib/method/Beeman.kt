package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d

object Beeman : Scheme {
    /**
     * @return X(n+1): newState`s coordinate */
    override fun calcCoordinate(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        currentState.coordinate + currentState.velocity * time + (currentState.accelerate * 4 - prevState.accelerate) * (time * time / 6)

    /**
     * @return V(n+1): newState`s velocity */
    override fun calcVelocity(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        currentState.velocity + (newState.accelerate * 2 + currentState.accelerate * 5 - prevState.accelerate) * (time / 6)
}