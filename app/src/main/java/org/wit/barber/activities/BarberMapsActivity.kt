package org.wit.barber.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.barber.R
import org.wit.barber.main.MainApp

import org.wit.barber.databinding.ActivityBarberMapsBinding
import org.wit.barber.databinding.ContentBarberMapsBinding

class BarberMapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarberMapsBinding
    private lateinit var map: GoogleMap
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBarberMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.title_activity_barber_maps)

        binding.contentBarberMaps.mapView.onCreate(savedInstanceState)
        app = application as MainApp

        binding.contentBarberMaps.mapView.getMapAsync {
            map = it
            configureMap()
        }
    }

    private fun configureMap() {
        map.uiSettings.isZoomControlsEnabled = true
        app.barbers.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options)?.tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))

        }
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
