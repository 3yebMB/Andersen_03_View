package dev.m13d.views_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var customLayout: LinearLayout
    private var counter = 0
    private val listLayout = listOf(
        R.layout.layout_austria,
        R.layout.layout_poland,
        R.layout.layout_italy,
        R.layout.layout_columbia,
        R.layout.layout_madagascar,
        R.layout.layout_thailand,
        R.layout.layout_thailand,
        R.layout.layout_switzerland
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customLayout = findViewById(R.id.custom_layout)
        findViewById<Button>(R.id.btn_next).setOnClickListener {
            if (counter < listLayout.size)
                counter++
            else
                counter = 0
            changeView(changeLayout(listLayout[counter]))
        }
        changeView(changeLayout(listLayout[counter]))
    }

    private fun changeLayout(id: Int) : View =
        LayoutInflater.from(this).inflate(id, null, false)

    private fun changeView(view: View) {
        customLayout.removeAllViews()
        customLayout.addView(view)
    }

}
