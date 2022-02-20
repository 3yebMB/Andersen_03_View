package dev.m13d.pictureloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import dev.m13d.pictureloader.MainActivity.Companion.BASE_URL
import dev.m13d.pictureloader.MainActivity.Companion.URL_TERMINATOR
import java.io.FileNotFoundException
import java.net.URL

open class LoaderImage(val input: String) : AsyncTask<String, Int, Bitmap>() {

    override fun doInBackground(vararg p0: String?): Bitmap {
        val url = URL(BASE_URL + input + URL_TERMINATOR)
        return try {
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: FileNotFoundException) {
            val conf = Bitmap.Config.ARGB_8888
            return Bitmap.createBitmap(1, 1, conf)
        }
    }
}
