package khatwa.zilalalrahmaapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainIntent = Intent(this@WelcomeActivity, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}