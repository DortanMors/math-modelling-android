package com.ssau.sunsystem.ui.model

import androidx.annotation.ColorInt
import com.ssau.sunsystemlib.entity.SpaceBody

data class UiSpaceBody(
    val name: String,
    val x: Float,
    val y: Float,
    @ColorInt
    val color: Int,
    val physic: SpaceBody,
)
