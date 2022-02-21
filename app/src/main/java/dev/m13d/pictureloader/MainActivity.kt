package dev.m13d.pictureloader

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import dev.m13d.pictureloader.databinding.ActivityMainBinding
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var url: URL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRequest.setOnClickListener {
            loader(BASE_URL + binding.adressInput.text.toString() + URL_TERMINATOR)
        }
    }

    private fun loader(imageUrl: String) {
        Picasso.get().load(imageUrl).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                binding.imageViewer.setImageBitmap(bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                binding.imageViewer.setImageResource(0)
                showError()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })
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
