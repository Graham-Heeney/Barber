package org.wit.barber.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.barber.databinding.ActivityBarberBinding
import org.wit.barber.main.MainApp
import org.wit.barber.models.BarberModel
import timber.log.Timber.i

class BarberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarberBinding
    var barber = BarberModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        i("Barber Activity started...")



        binding.btnAdd.setOnClickListener {
            barber.title = binding.barberTitle.text.toString()
            barber.description = binding.barberDescription.text.toString()
            if (barber.title.isNotEmpty()) {
                app.barbers.add(barber.copy())
                i("Add Button Pressed: $barber")
                for (i in app.barbers.indices) {
                    i("Barber[$i]: ${this.app.barbers[i]}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a title", Snackbar.LENGTH_LONG).show()
            }
        }

    }
}
