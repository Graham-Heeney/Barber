package org.wit.barber.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.barber.R
import org.wit.barber.adapters.BarberAdapter
import org.wit.barber.adapters.BarberListener
import org.wit.barber.databinding.ActivityBarberListBinding
import org.wit.barber.main.MainApp
import org.wit.barber.models.BarberModel

class BarberListActivity : AppCompatActivity(), BarberListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityBarberListBinding
    private lateinit var barberAdapter: BarberAdapter

    private val mapIntentLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBarberListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        barberAdapter = BarberAdapter(app.barbers.findAll(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = barberAdapter

        binding.fabAddBarber.setOnClickListener {
            val launcherIntent = Intent(this, BarberActivity::class.java)
            getResult.launch(launcherIntent)
        }






    app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        barberAdapter.updateData(app.barbers.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.item_search)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        searchView.queryHint = "Search barbers..."
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                barberAdapter.filter.filter(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                barberAdapter.filter.filter(newText ?: "")
                return false
            }
        })

        return true
    }




    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                barberAdapter.updateData(app.barbers.findAll())
            }
        }




    override fun onBarberClick(barber: BarberModel) {
        val launcherIntent = Intent(this, BarberActivity::class.java)
        launcherIntent.putExtra("barber_edit", barber)
        getClickResult.launch(launcherIntent)
    }



    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                barberAdapter.updateData(app.barbers.findAll())
            }
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, BarberActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.item_map -> {
                val launcherIntent = Intent(this, BarberMapsActivity::class.java)
                mapIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
