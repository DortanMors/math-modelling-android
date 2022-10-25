package com.ssau.sunsystem.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.ssau.sunsystem.ui.model.UiSpaceBody
import com.ssau.sunsystemlib.core.Constants.DEFAULT_RADIUS
import com.ssau.sunsystemlib.core.Constants.METERS_PER_PIXEL

class PlanetSystemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context, attrs, defStyle) {

    private var planets: List<UiSpaceBody> = emptyList()


    companion object {
        private const val STROKE_WIDTH = 5f
    }

    private lateinit var paths: Array<Path> //todo fix shit

    fun setInitialPlanetsCoordinates(planets: List<UiSpaceBody>) {
        paths = Array(planets.size) { index ->
            val coordinate = planets[index].coordinate
            Path().apply {
                moveTo(coordinate.x.mapToUiX(), coordinate.y.mapToUiY())
            }
        }
    }

    private val pathPaint = Paint()
        .apply {
            color = Color.RED
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeWidth = STROKE_WIDTH
        }

    private val planetPaint = Paint().apply {
        color = Color.WHITE
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        Log.d("HARDCODE", "\nSTATE")
        planets.forEachIndexed { index, planet ->
            val cx = planet.coordinate.x.mapToUiX()
            val cy = planet.coordinate.y.mapToUiY()
            paths[index].lineTo(planet.coordinate.x.mapToUiX(), planet.coordinate.y.mapToUiY())
            canvas.drawPath(paths[index], pathPaint.apply { color = planet.color })
            canvas.drawCircle(
                /*cx = */ cx,
                /*cy = */ cy,
                /*radius = */ DEFAULT_RADIUS,
                /*paint = */ planetPaint.apply { color = planet.color },
            )
        }
    }

    fun setSystemState(planets: List<UiSpaceBody>) {
        this.planets = planets
        invalidate()
    }

    private fun Double.mapToUiX(): Float = centerX + toFloat() / METERS_PER_PIXEL

    private fun Double.mapToUiY(): Float = centerY + toFloat() / METERS_PER_PIXEL

    private val centerY
        get() = measuredHeight / 2

    private val centerX
        get() = measuredWidth / 2

}