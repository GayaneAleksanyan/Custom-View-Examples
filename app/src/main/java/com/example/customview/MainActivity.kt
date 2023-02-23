package com.example.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class CustomView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) :
    View(context, attributeSet) {

    private var paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private var coloredPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 100f
    }

    private var circlePaint = Paint().apply {
        color = Color.MAGENTA
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 20f
    }

    private var trianglePaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 5f
    }

    private var strokePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.CYAN
        strokeWidth = 5f
        flags = Paint.ANTI_ALIAS_FLAG
    }

    private var textPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        textSize = 150f
        typeface = Typeface.SANS_SERIF
        color = Color.BLACK
        isAntiAlias = true
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paint)
        canvas.drawLine(width.toFloat(), 0f, 0f, height.toFloat(), paint)
        canvas.drawPoint(width / 2f, height / 2f, coloredPaint)
        canvas.drawCircle(width / 4f, height / 4f, 50f, circlePaint)
        canvas.drawRect(100f, 100f, 300f, 300f, circlePaint)

        val b = Point(100, 200)
        val c = Point(260, 150)
        val a = Point(100, 100)

        val path = Path()
        path.fillType = Path.FillType.EVEN_ODD
        path.moveTo(b.x.toFloat(), b.y.toFloat())
        path.lineTo(c.x.toFloat(), c.y.toFloat())
        path.lineTo(a.x.toFloat(), a.y.toFloat())
        path.close()
        canvas.drawPath(path, trianglePaint)

        val textPath = Path()
        textPath.addCircle(width / 2f, height / 2f, 400f, Path.Direction.CW)
        canvas.drawCircle(width / 2f, height / 2f, 400f, strokePaint)
        canvas.drawTextOnPath("My Text", textPath, 0f, -30f, textPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val colors = intArrayOf(
            Color.YELLOW,
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA,
            Color.RED
        )
        val positions = floatArrayOf(
            0.0f,
            0.2f,
            0.4f,
            0.6f,
            0.8f
        )
        circlePaint.shader = LinearGradient(
            0f, 0f, 0f, h.toFloat(),
            colors, positions, Shader.TileMode.MIRROR
        )

        paint.shader = LinearGradient(
            0f, 0f, 0f, h.toFloat(),
            Color.YELLOW, Color.RED, Shader.TileMode.MIRROR
        )

        trianglePaint.shader = RadialGradient(
            w.toFloat() / 2, h.toFloat() / 2, 100f,
            Color.YELLOW, Color.RED, Shader.TileMode.REPEAT
        )
    }
}

