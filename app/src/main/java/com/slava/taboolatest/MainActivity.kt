package com.slava.taboolatest

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.slava.taboolatest.articles.fragments.MainArticlesScreenFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var fragmentContainer: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainArticlesScreenFragment.newInstance(), null)
            .commit()

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    fragmentContainer = findViewById(R.id.fragment_container)
                    handleSendText(intent) // Handle text being sent
                }
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let { colorHex ->
            Timber.d("Received color - $colorHex")
            fragmentContainer?.setBackgroundColor(Color.parseColor(colorHex))
        }
    }
}
