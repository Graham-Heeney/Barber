package org.wit.barber.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.barber.R
import org.wit.barber.databinding.ActivityBarberBinding
import org.wit.barber.main.MainApp
import org.wit.barber.models.BarberModel
import timber.log.Timber
import timber.log.Timber.i


class BarberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarberBinding
    var barber = BarberModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        if (intent.hasExtra("barber_edit")) {
            barber = intent.getParcelableExtra("barber_edit")!!
            binding.barberName.setText(barber.title)
            binding.barberAddress.setText(barber.description)
        }

        binding.btnAdd.setOnClickListener {
            barber.title = binding.barberName.text.toString()
            barber.description = binding.barberAddress.text.toString()

            if (barber.title.isNotEmpty()) {
                if (intent.hasExtra("barber_edit")) {
                    app.barbers.update(barber.copy())
                } else {
                    app.barbers.create(barber.copy())
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a title", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.chooseImage.setOnClickListener {
            i("Select image")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_barber, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                setResult(RESULT_CANCELED)
                finish()
            }

            R.id.item_delete -> {
                if (intent.hasExtra("barber_edit")) {
                    app.barbers.delete(barber)
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
