package org.wit.barber.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.barber.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("auth", MODE_PRIVATE)

        if (!prefs.contains("pin")) {
            prefs.edit().putString("pin", "1234").apply()
        }

        binding.btnLogin.setOnClickListener {
            val enteredPin = binding.pinInput.text.toString()
            val savedPin = prefs.getString("pin", "1234")

            if (enteredPin == savedPin) {
                startActivity(Intent(this, BarberListActivity::class.java))
                finish()
            } else {
                Snackbar.make(it, "Incorrect PIN", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
