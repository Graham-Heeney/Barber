package org.wit.barber.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.barber.models.BarberModel
import org.wit.barber.databinding.ActivityBarberBinding
import timber.log.Timber
import timber.log.Timber.i

class BarberActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBarberBinding
    var barber = BarberModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityBarberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        Timber.plant(Timber.DebugTree())
        
        i("Barber Activity started...")
        
        binding.btnAdd.setOnClickListener() {
            barber.title = binding.barberTitle.text.toString()
            if (barber.title.isNotEmpty()) {
                i("add Button Pressed: $barber.title")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}