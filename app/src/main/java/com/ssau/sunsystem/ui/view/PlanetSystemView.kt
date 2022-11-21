package com.ssau.sunsystem.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.ssau.sunsystem.Defaults
import com.ssau.sunsystem.Defaults.DEFAULT_RADIUS
import com.ssau.sunsystem.ui.model.UiSpaceBody
import com.ssau.sunsystem.util.drawArrow
import com.ssau.sunsystem.util.formatDouble
import com.ssau.sunsystem.util.toDegrees
import kotlin.math.atan2

class PlanetSystemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context, attrs, defStyle) {

    companion object {
        private const val STROKE_WIDTH = 5f
        private val pathPaint = Paint()
            .apply {
                color = Color.RED
                isAntiAlias = true
                style = Paint.Style.STROKE
                strokeJoin = Paint.Join.ROUND
                strokeWidth = STROKE_WIDTH
            }

        private val velocityPaint = Paint()
            .apply {
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = STROKE_WIDTH
            }

        private val forcePaint = Paint()
            .apply {
                color = Color.BLUE
                style = Paint.Style.STROKE
                strokeWidth = STROKE_WIDTH
            }

        private val planetPaint = Paint().apply {
            color = Color.WHITE
            isAntiAlias = true
        }

    }

    var showPaths: Boolean = true
        set(value) {
            field = value
            invalidate()
        }
    var showPhysics: Boolean = false
        set(value) {
            field = value
            invalidate()
        }
    var showNames: Boolean = true
        set(value) {
            field = value
            invalidate()
        }

    private val textPaint = Paint()
        .apply {
            color = Color.BLACK
            textSize = Defaults.PLANET_TEXT_SIZE.sp()
        }

    private var planets: List<UiSpaceBody> = emptyList()

    private lateinit var paths: Array<Path>

    fun setInitialPlanetsCoordinates(planets: List<UiSpaceBody>) {
        paths = Array(planets.size) { index ->
            Path().apply {
                with(planets[index]) {
                    moveTo(x, y)
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        planets.forEachIndexed { index, planet ->
            val planetX = planet.x
            val planetY = planet.y
            paths[index].lineTo(planet.x, planet.y)
            if (showPaths) {
                canvas.drawPath(paths[index], pathPaint.apply { color = planet.color })
            }
            if (showPhysics) {
                canvas.drawVectorArrow(
                    x0 = planetX,
                    y0 = planetY,
                    x = planet.physic.velocity.x.toFloat(),
                    y = planet.physic.velocity.y.toFloat(),
                    paint = velocityPaint,
                )
                canvas.drawVectorArrow(
                    x0 = planetX,
                    y0 = planetY,
                    x = planet.physic.externalForce.x.toFloat(),
                    y = planet.physic.externalForce.y.toFloat(),
                    paint = forcePaint,
                )
            }
            canvas.drawInfo(
                body = planet,
                paint = textPaint,
            )
            canvas.drawCircle(
                /*cx =     */ planetX,
                /*cy =     */ planetY,
                /*radius = */ DEFAULT_RADIUS,
                /*paint =  */ planetPaint.apply { color = planet.color },
            )
        }
    }

    fun setSystemState(planets: List<UiSpaceBody>) {
        this.planets = planets
        invalidate()
    }

    private fun Float.dp(): Float = this * resources.displayMetrics.density

    private fun Float.sp(): Float = this * resources.displayMetrics.scaledDensity

    private fun Canvas.drawVectorArrow(
        x0: Float,
        y0: Float,
        x: Float,
        y: Float,
        paint: Paint,
    ) {
        val angle = atan2(y, x).toDegrees()
        val arrowLength = Defaults.ARROW_LENGTH.dp()
        drawArrow(x0, y0, angle, paint, arrowLength, Defaults.ARROW_WIDTH.dp())
    }

    private fun Canvas.drawInfo(
        body: UiSpaceBody,
        paint: Paint,
    ) {
        if (showNames) {
            drawText(
                body.name,
                body.x + Defaults.ARROW_LENGTH.dp(),
                body.y - Defaults.PLANET_TEXT_SIZE.sp(),
                paint,
            )
        }
        if (showPhysics) {
            drawText(
                "v: ${body.physic.velocity.abs().formatDouble()}",
                body.x + Defaults.ARROW_LENGTH.dp(),
                body.y,
                paint
            )
            drawText(
                "a: ${body.physic.accelerate.abs().formatDouble()}",
                body.x + Defaults.ARROW_LENGTH.dp(),
                body.y + Defaults.PLANET_TEXT_SIZE.sp(),
                paint
            )
            drawText(
                "f: ${body.physic.externalForce.abs().formatDouble()}",
                body.x + Defaults.ARROW_LENGTH.dp(),
                body.y + 2 * Defaults.PLANET_TEXT_SIZE.sp(),
                paint
            )
        }
    }
}
