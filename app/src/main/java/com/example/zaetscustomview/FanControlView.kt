package com.example.zaetscustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class FanControlView @JvmOverloads constructor(context: Context, private val attributeSet: AttributeSet? = null) : View(context, attributeSet) {
    private var size = 0
    private val paint = Paint()
    private var color: Int = DEFAULT_FAN_CONTROL_COLOR
    var currentStep = DEFAULT_FAN_CONTROL_STEP
        private set
    private var fontSize = context.resources.getDimension(DEFAULT_FAN_CONTROL_FONT_SIZE)

    init {
        setupAttributes()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            if (currentStep < 3) currentStep++
            else currentStep = 0
            invalidate()
        }
        return super.onTouchEvent(event)
    }

    private fun setupAttributes() {
        val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.FanControlView, 0, 0)

        color = typedArray.getColor(R.styleable.FanControlView_color, DEFAULT_FAN_CONTROL_COLOR)
        fontSize = typedArray.getDimension(R.styleable.FanControlView_fontSize, fontSize)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawCircle(it)
            drawText(it)
            drawCurrStep(it)
        }
    }

    private fun drawCurrStep(canvas: Canvas) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL

        when(currentStep) {
            0 -> canvas.drawCircle(size * 0.2f, size * 0.35f, size * 0.05f, paint)
            1 -> canvas.drawCircle(size * 0.32f, size * 0.23f, size * 0.05f, paint)
            2 -> canvas.drawCircle(size * 0.68f, size * 0.22f, size * 0.05f, paint)
            3 -> canvas.drawCircle(size * 0.80f, size * 0.35f, size * 0.05f, paint)
        }
    }

    private fun drawText(canvas: Canvas) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.textSize = fontSize

        canvas.drawText("0", size * 0.07f, size * 0.35f, paint)
        canvas.drawText("1", size * 0.25f, size * 0.15f, paint)
        canvas.drawText("2", size * 0.71f, size * 0.15f, paint)
        canvas.drawText("3", size * 0.87f, size * 0.35f, paint)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.color = color
        paint.style = Paint.Style.FILL

        canvas.drawCircle(size * 0.5f, size * 0.5f, size * 0.4f, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        size = width.coerceAtMost(height)
        setMeasuredDimension(size, size)
    }
}