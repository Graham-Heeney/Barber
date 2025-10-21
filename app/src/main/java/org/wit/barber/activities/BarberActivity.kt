package org.wit.barber.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
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
        binding.toolbar.title = "Add Barber"
        setSupportActionBar(binding.toolbar)

        binding.btnAdd.setOnClickListener {
            barber.title = binding.barberTitle.text.toString()
            barber.description = binding.barberDescription.text.toString()
            if (barber.title.isNotEmpty()) {
                app.barbers.add(barber.copy())
                i("Add Button Pressed: $barber")
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a title", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(org.wit.barber.R.menu.menu_barber, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            org.wit.barber.R.id.item_cancel -> {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
