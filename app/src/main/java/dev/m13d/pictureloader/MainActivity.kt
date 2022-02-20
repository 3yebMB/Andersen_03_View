package dev.m13d.pictureloader

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.m13d.pictureloader.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRequest.setOnClickListener {
            loader(binding.adressInput.text.toString())
        }
    }

    private fun loader(input: String) {
        val url = URL(BASE_URL + input + URL_TERMINATOR)
        Thread {
            try {
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                binding.imageViewer.post {
                    binding.imageViewer.setImageBitmap(bitmap)
                }
            } catch (e: FileNotFoundException) {
                binding.imageViewer.post {
                    binding.imageViewer.setImageResource(0)
                }
                showToast(ERROR)
            }
        }.start()
    }

    private fun showToast(message: String?) {
        runOnUiThread {
            val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    companion object {
        const val BASE_URL = "https://picsum.photos/id/"
        const val URL_TERMINATOR = "/320/320"
        const val ERROR = """
            You put incorrect picture id.
                Please try again. 
        """
    }
}
