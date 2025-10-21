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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarberListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = BarberAdapter(app.barbers.findAll(), this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, BarberActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(
                    0,
                    app.barbers.findAll().size
                )
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
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(
                    0,
                    app.barbers.findAll().size
                )
            }
        }
}
