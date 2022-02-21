package dev.m13d.pictureloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.m13d.pictureloader.databinding.ActivityMainBinding
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        binding.buttonRequest.setOnClickListener {

            executor.execute {
                try {
                    val url = URL(BASE_URL + binding.adressInput.text.toString() + URL_TERMINATOR)
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                    handler.post {
                        binding.imageViewer.setImageBitmap(bitmap)
                    }
                } catch (e: Exception) {
                    handler.post {
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
