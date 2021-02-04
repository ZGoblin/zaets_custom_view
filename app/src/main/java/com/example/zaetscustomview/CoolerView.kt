package com.example.zaetscustomview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class CoolerView @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null
) : AppCompatImageView(context, attributeSet) {
    private var widthView = 0
    private var heightView = 0
    private var rotationDegrees: Int = 0
    var speed: Int = 0
        set(value) {
            field = if (value >= 0) {
                value
            } else {
                0
            }
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        widthView = MeasureSpec.getSize(widthMeasureSpec)
        heightView = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(widthView, heightView)
    }

    private fun setupAttributes() {
        val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.CoolerView, 0, 0)

        val src = typedArray.getResourceId(R.styleable.CoolerView_src, R.drawable.ic_fan)
        setImageResource(src)

        typedArray.recycle()
    }

    init {
        setupAttributes()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate((widthView / 2).toFloat(), (heightView / 2).toFloat())
        canvas.rotate(rotation(speed).toFloat())
        canvas.translate((-widthView / 2).toFloat(), (-heightView / 2).toFloat())
        postInvalidateOnAnimation()
        super.onDraw(canvas)
    }

    private fun rotation(speed: Int): Int {
        rotationDegrees = (rotationDegrees + speed) % CIRCUMFERENCE_OF_CIRCLE
        return rotationDegrees
    }
}