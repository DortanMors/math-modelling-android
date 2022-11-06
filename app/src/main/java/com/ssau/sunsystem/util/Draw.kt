package com.ssau.sunsystem.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import com.ssau.sunsystem.Defaults


fun Canvas.drawArrow(
    x0: Float,
    y0: Float,
    angle: Float,
    paint: Paint,
    arrowLength: Float,
    arrowWidth: Float,
) {
    val arrowPath = Path().apply {
        moveTo(x0, y0)
        lineTo(x0 + arrowLength, y0)
        lineTo(x0 + arrowLength - Defaults.ARROW_HEAD_LENGTH * arrowWidth, y0 + arrowWidth)
        moveTo(x0 + arrowLength, y0)
        lineTo(x0 + arrowLength - Defaults.ARROW_HEAD_LENGTH * arrowWidth, y0 - arrowWidth)
        rotate(angle, x0, y0)
    }
    drawPath(arrowPath, paint)
}

private fun Path.rotate(
    angle: Float,
    x0: Float = 0f,
    y0: Float = 0f
) {
    val matrix = android.graphics.Matrix()
    matrix.setRotate(angle, x0, y0)
    transform(matrix)
}
