package com.ssau.sunsystem.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.ssau.sunsystemlib.core.interfaces.PlanetSystem

class PlanetSystemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context, attrs, defStyle) {

    var planetSystem: PlanetSystem? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }

    companion object {
        private const val STROKE_WIDTH = 5f
    }

    private val paint = Paint()
        .apply {
            color = Color.WHITE
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeWidth = STROKE_WIDTH
        }

    private val path = Path()




}