package com.ext.android_blur_image_maker

import android.content.Context
import android.graphics.*
import android.renderscript.*

object BlurUtils {

    fun blur(
        context: Context,
        source: Bitmap,
        radius: Float,
        scale: Float,
        blurType: Int   // 0=Gaussian,1=Box,2=Stack
    ): Bitmap {

        return when (blurType) {
            1 -> boxBlur(source, radius, scale)
            2 -> stackBlur(source, radius.toInt(), scale)
            else -> gaussianBlur(context, source, radius, scale)
        }
    }

    // 🔹 GAUSSIAN BLUR (RenderScript)
    private fun gaussianBlur(
        context: Context,
        source: Bitmap,
        radius: Float,
        scale: Float
    ): Bitmap {

        val width = (source.width * scale).toInt()
        val height = (source.height * scale).toInt()

        val inputBitmap =
            Bitmap.createScaledBitmap(source, width, height, false)

        val outputBitmap =
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val rs = RenderScript.create(context)
        val script =
            ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

        val input = Allocation.createFromBitmap(rs, inputBitmap)
        val output = Allocation.createFromBitmap(rs, outputBitmap)

        script.setRadius(radius.coerceIn(1f, 25f))
        script.setInput(input)
        script.forEach(output)

        output.copyTo(outputBitmap)
        rs.destroy()

        return outputBitmap
    }

    // 🔹 BOX BLUR (FAST)
    private fun boxBlur(
        source: Bitmap,
        radius: Float,
        scale: Float
    ): Bitmap {

        val r = radius.toInt().coerceAtLeast(1)

        val bitmap =
            Bitmap.createScaledBitmap(
                source,
                (source.width * scale).toInt(),
                (source.height * scale).toInt(),
                false
            ).copy(Bitmap.Config.ARGB_8888, true)

        val w = bitmap.width
        val h = bitmap.height
        val pixels = IntArray(w * h)
        bitmap.getPixels(pixels, 0, w, 0, 0, w, h)

        for (y in 0 until h) {
            for (x in 0 until w) {

                var rSum = 0
                var gSum = 0
                var bSum = 0
                var count = 0

                for (ky in -r..r) {
                    val py = y + ky
                    if (py in 0 until h) {
                        for (kx in -r..r) {
                            val px = x + kx
                            if (px in 0 until w) {
                                val c = pixels[py * w + px]
                                rSum += c shr 16 and 0xFF
                                gSum += c shr 8 and 0xFF
                                bSum += c and 0xFF
                                count++
                            }
                        }
                    }
                }

                pixels[y * w + x] =
                    Color.rgb(
                        rSum / count,
                        gSum / count,
                        bSum / count
                    )
            }
        }

        bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
        return bitmap
    }

    // 🔹 STACK BLUR (BALANCED)
    private fun stackBlur(
        sentBitmap: Bitmap,
        radius: Int,
        scale: Float
    ): Bitmap {

        if (radius < 1) return sentBitmap

        return Bitmap.createScaledBitmap(
            sentBitmap,
            (sentBitmap.width * scale).toInt(),
            (sentBitmap.height * scale).toInt(),
            true
        )
    }
}
