package com.ssau.sunsystem.util

import com.ssau.sunsystem.ui.model.UiSpaceBody
import com.ssau.sunsystemlib.entity.SpaceBody

fun SpaceBody.mapToUi() = UiSpaceBody(
    coordinate = this.coordinate,
    color = this.colorId,
)
