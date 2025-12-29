package com.ext.android_blur_image_maker

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class BlurImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    private var blurRadius = 20f
    private var blurScale = 0.3f
    private var blurEnabled = true
    private var blurType = 0   // 0=Gaussian,1=Box,2=Stack
    private var overlayColor = Color.TRANSPARENT
    private var overlayAlpha = 0f

    init {
        context.obtainStyledAttributes(attrs, R.styleable.BlurImageView).apply {

            blurRadius =
                getFloat(R.styleable.BlurImageView_blurRadius, 20f)

            blurScale =
                getFloat(R.styleable.BlurImageView_blurScale, 0.3f)

            blurEnabled =
                getBoolean(R.styleable.BlurImageView_blurEnabled, true)

            blurType =
                getInt(R.styleable.BlurImageView_blurType, 0)

            overlayColor =
                getColor(R.styleable.BlurImageView_overlayColor, Color.TRANSPARENT)

            overlayAlpha =
                getFloat(R.styleable.BlurImageView_overlayAlpha, 0f)

            val img =
                getResourceId(R.styleable.BlurImageView_blurImageSrc, -1)
            if (img != -1) setImageResource(img)

            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (!blurEnabled || drawable == null) {
            super.onDraw(canvas)
            return
        }

        val bitmap = (drawable as BitmapDrawable).bitmap

        val blurred =
            BlurUtils.blur(
                context,
                bitmap,
                blurRadius,
                blurScale,
                blurType
            )

        canvas.drawBitmap(
            blurred,
            null,
            Rect(0, 0, width, height),
            null
        )

        if (overlayAlpha > 0f) {
            val paint = Paint()
            paint.color = overlayColor
            paint.alpha = (overlayAlpha * 255).toInt()
            canvas.drawRect(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                paint
            )
        }
    }
}
