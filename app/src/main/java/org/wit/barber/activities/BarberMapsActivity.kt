package org.wit.barber.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import org.wit.barber.R
import org.wit.barber.main.MainApp

import org.wit.barber.databinding.ActivityBarberMapsBinding
import org.wit.barber.databinding.ContentBarberMapsBinding

class BarberMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

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
            map.setOnMarkerClickListener(this)


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

    override fun onMarkerClick(marker: Marker): Boolean {
        //val placemark = marker.tag as PlacemarkModel
        val tag = marker.tag as Long
        val placemark = app.barbers.findById(tag)
        binding.contentBarberMaps.currentTitle.text = placemark!!.title
        binding.contentBarberMaps.currentDescription.text = placemark.description
        Picasso.get().load(placemark.image).into(binding.contentBarberMaps.currentImage)
        return false
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_maps, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_close -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
