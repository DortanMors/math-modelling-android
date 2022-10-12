package com.ssau.sunsystemlib.entity

import com.ssau.sunsystemlib.util.Vector3d

data class SpaceBody(
    val mass: Double,
    val coordinate: Vector3d,
    val velocity: Vector3d,
    val externalForce: Vector3d,
    val colorId: Int,
) {
    val accelerate: Vector3d
        get() = externalForce / mass
}