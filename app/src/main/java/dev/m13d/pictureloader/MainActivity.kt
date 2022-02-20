package dev.m13d.pictureloader

import android.graphics.Bitmap
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.m13d.pictureloader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRequest.setOnClickListener {
            val input = binding.adressInput.text.toString()
            val loader = object : LoaderImage(input) {
                override fun onPostExecute(result: Bitmap?) {
                    super.onPostExecute(result)
                    if (result != null && result.height != 1)
                        binding.imageViewer.setImageBitmap(result)
                    else {
                        binding.imageViewer.setImageResource(0)
                        showError()
                    }
                }
            }
            loader.execute()
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
