package com.ssau.sunsystem.ui.model

import androidx.annotation.ColorInt
import com.ssau.sunsystemlib.util.Vector3d

data class UiSpaceBody(
    val coordinate: Vector3d,
    @ColorInt
    val color: Int,
)
