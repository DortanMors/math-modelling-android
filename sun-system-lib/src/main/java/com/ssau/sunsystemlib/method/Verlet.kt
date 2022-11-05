package com.ssau.sunsystemlib.method

import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d

object Verlet : DifferenceScheme() {
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
     * @return V(n+1): currentState`s coordinate */
    override fun calcVelocity(
        prevState: SpaceBody,
        currentState: SpaceBody,
        newState: SpaceBody,
        time: Long
    ): Vector3d =
        (calcCoordinate(prevState, currentState, newState, time) * 2 - currentState.coordinate + newState.accelerate * time * time - currentState.coordinate) / (2 * time)
}
// x(n+1)=2xn-x(n-1) + a * dt^2
// v(n) = (x(n+1) - x(n-1)) / (2dt)
// v(n+1) = (x(n+2) - x(n)) / (2dt) = (2x(n+1) - x(n) + a * dt^2 - x(n)) / (2dt)
// x(n+2) = 2x(n+1) - x(n) + a * dt^2