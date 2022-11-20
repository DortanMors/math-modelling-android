package com.ssau.sunsystem.ui.screen.planets

data class CustomizedPlanet(
    var name: String,
    var mass: Double,
    var color: Int,
    var x: Double = 0.0,
    var y: Double = 0.0,
    var z: Double = 0.0,
    var velocityX: Double = 0.0,
    var velocityY: Double = 0.0,
    var velocityZ: Double = 0.0,
)