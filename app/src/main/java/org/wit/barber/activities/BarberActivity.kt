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
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.squareup.picasso.Picasso
import org.wit.barber.helpers.showImagePicker



class BarberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarberBinding
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>


    var barber = BarberModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBarberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.barberLocation.setOnClickListener {
            i ("Set Location Pressed")
        }
        binding.barberLocation.setOnClickListener {
            i("Set Location Pressed")
            val launcherIntent = Intent(this, MapActivity::class.java)
            mapIntentLauncher.launch(launcherIntent)
        }


        registerImagePickerCallback()
        registerMapCallback()

        binding.chooseImage.setOnClickListener {
            showImagePicker(this, imageIntentLauncher)
        }

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
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_barber, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { i("Map Loaded") }
    }


    private fun registerImagePickerCallback() {
        if (intent.hasExtra("barber_edit")) {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            barber.image = result.data!!.data!!
                            Picasso.get()
                                .load(barber.image)
                                .into(binding.barberImage)
                            if (barber.image != Uri.EMPTY) {
                                binding.chooseImage.setText(R.string.change_barber_image)
                            }

                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }}




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
