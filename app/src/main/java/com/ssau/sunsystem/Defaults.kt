
package com.ssau.sunsystem

import android.graphics.Color
import com.ssau.sunsystem.ui.screen.planets.CustomizedPlanet
import com.ssau.sunsystemlib.method.Euler

object Defaults {
    const val ARROW_WIDTH: Float = 2f
    const val ARROW_HEAD_LENGTH: Float = 4f
    const val ARROW_LENGTH: Float = 15f
    const val PLANET_TEXT_SIZE: Float = 12f
    const val DEFAULT_RADIUS: Float = 10f // todo желательно вычислять на основе массы
    const val SCALE_STEP: Float = 5E7f
    var METERS_PER_PIXEL: Float = 10 * SCALE_STEP
    const val DEFAULT_TIMESTEP: Long = 60 * 60 * 24
    const val DEFAULT_DELAY: Long = 100

    val scheme = Euler // стартовая схема
    val planetsUi: List<CustomizedPlanet> = listOf(
        CustomizedPlanet( // Светило
            mass = 1.989E30,
            color = Color.YELLOW,
            name = "Солнце",
        ),
        CustomizedPlanet( // Mercury Development
            mass = 3.285E23,
            x = 58E9,
            velocityY = 48E3,
            color = Color.RED,
            name = "Меркурий",
        ),
        CustomizedPlanet( // Venus Anus
            mass = 4.867E24,
            x = 108E9,
            velocityY = 35E3,
            color = Color.MAGENTA,
            name = "Венера",
        ),
        CustomizedPlanet( // Terra Internum
            mass = 5.974E24,
            x = 150E9,
            velocityY = 30E3,
            color = Color.BLUE,
            name = "Земля",
        )
    )
}