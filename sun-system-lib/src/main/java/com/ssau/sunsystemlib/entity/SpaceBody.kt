package com.ssau.sunsystemlib.entity

import com.ssau.sunsystemlib.util.Vector3d

data class SpaceBody(
    val mass: Double,
    var coordinate: Vector3d,
    var velocity: Vector3d,
    var externalForce: Vector3d,
) {
    val accelerate: Vector3d
        get() = externalForce / mass
}