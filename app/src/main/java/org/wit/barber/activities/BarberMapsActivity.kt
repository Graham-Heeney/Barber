package org.wit.barber.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import org.wit.barber.R

import org.wit.barber.databinding.ActivityBarberMapsBinding
import org.wit.barber.databinding.ContentBarberMapsBinding

class BarberMapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarberMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBarberMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.title_activity_barber_maps)

        // ✅ LAB WAY: access included layout via binding
        binding.contentBarberMaps.mapView.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        binding.contentBarberMaps.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.contentBarberMaps.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.contentBarberMaps.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.contentBarberMaps.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.contentBarberMaps.mapView.onSaveInstanceState(outState)
    }
}



