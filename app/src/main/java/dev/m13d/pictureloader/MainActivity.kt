package dev.m13d.pictureloader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        }
    }

    companion object {
        const val BASE_URL = "https://picsum.photos/id/"
        const val URL_TERMINATOR = "//320//320"
    }
}
