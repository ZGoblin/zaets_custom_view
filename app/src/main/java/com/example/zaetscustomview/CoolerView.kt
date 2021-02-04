package com.example.zaetscustomview

import android.R
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class CoolerView @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null
) : View(context, attributeSet) {
    private var size = 0
    private val paint = Paint()


//    private fun setupAttributes() {
//        val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.FanControlView, 0, 0)
//
//        color = typedArray.getColor(R.styleable.FanControlView_color, DEFAULT_FAN_CONTROL_COLOR)
//        fontSize = typedArray.getDimension(R.styleable.FanControlView_fontSize, fontSize)
//
//        typedArray.recycle()
//    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        canvas?.let {
//        }
//    }
//
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//
//        val width = MeasureSpec.getSize(widthMeasureSpec)
//        val height = MeasureSpec.getSize(heightMeasureSpec)
//        size = width.coerceAtMost(height)
//        setMeasuredDimension(size, size)
//    }

    private fun init() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_fa)
        setImageBitmap(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        // Translate rotation axe to the center.
        canvas.translate((canvas.width / 2).toFloat(), (canvas.height / 2).toFloat())
        // Rotate!
        canvas.rotate(rotation(3).toFloat())
        // Put back to its original place.
        canvas.translate((-canvas.width / 2).toFloat(), (-canvas.height / 2).toFloat())
        // Invalidate the view.
        postInvalidateOnAnimation()
        super.onDraw(canvas)
    }

    private fun rotation(delta: Int): Int {
        rotationDegrees = (rotationDegrees + delta) % 360
        return rotationDegrees
    }
}