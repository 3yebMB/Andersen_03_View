package dev.m13d.pictureloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dev.m13d.pictureloader.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainExecutor: Executor = ContextCompat.getMainExecutor(this)
        val backgroundExecutor = Executors.newSingleThreadScheduledExecutor()

        binding.buttonRequest.setOnClickListener {
            val url = URL(BASE_URL + binding.adressInput.text.toString() + URL_TERMINATOR)

            backgroundExecutor.execute {
                bitmap = try {
                    BitmapFactory.decodeStream(url.openConnection().getInputStream())
                } catch (e: FileNotFoundException) {
                    Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                }
                mainExecutor.execute {
                    if (bitmap != null && bitmap?.height != 1)
                        binding.imageViewer.setImageBitmap(bitmap)
                    else {
                        binding.imageViewer.setImageResource(0)
                        showError()
                    }
                }
            }
        }
    }

    private fun showError() {
        val toast = Toast.makeText(this, ERROR, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    companion object {
        const val BASE_URL = "https://picsum.photos/id/"
        const val URL_TERMINATOR = "//320//320"
        const val ERROR = """
            You put incorrect picture id.
                Please try again. 
        """
    }
}
