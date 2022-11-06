package com.ssau.sunsystem

import android.graphics.Color
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.method.Euler
import com.ssau.sunsystemlib.util.Vector3d

object Defaults {
    const val ARROW_WIDTH: Float = 2f
    const val ARROW_HEAD_LENGTH: Float = 4f
    const val ARROW_LENGTH: Float = 15f
    const val PLANET_TEXT_SIZE: Float = 12f
    const val DEFAULT_RADIUS: Float = 10f
    const val METERS_PER_PIXEL: Float = 5E8f
    const val DEFAULT_TIMESTEP: Long = 60 * 60 * 24
    val scheme = Euler
    val planets: List<SpaceBody> = listOf(
        SpaceBody( // Светило
            mass = 1.989E30,
            coordinate = Vector3d(0.0, 0.0, 0.0),
            velocity = Vector3d(0.0, 0.0, 0.0),
            externalForce = Vector3d(0.0, 0.0, 0.0),
            colorId = Color.YELLOW,
        ),
        SpaceBody( // Mercury Development
            mass = 3.285E23,
            coordinate = Vector3d(58E9, 0.0, 0.0),
            velocity = Vector3d(0.0, 48E3, 0.0),
            externalForce = Vector3d(0.0, 0.0, 0.0),
            colorId = Color.RED,
        ),
        SpaceBody( // Venus Anus
            mass = 4.867E24,
            coordinate = Vector3d(108E9, 0.0, 0.0),
            velocity = Vector3d(0.0, 35E3, 0.0),
            externalForce = Vector3d(0.0, 0.0, 0.0),
            colorId = Color.MAGENTA,
        ),
        SpaceBody( // Terra Internum
            mass = 5.974E24,
            coordinate = Vector3d(150E9, 0.0, 0.0),
            velocity = Vector3d(0.0, 30E3, 0.0),
            externalForce = Vector3d(0.0, 0.0, 0.0),
            colorId = Color.BLUE,
        )
    )
}