package org.wit.barber.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.barber.R
import org.wit.barber.databinding.ActivityBarberListBinding
import org.wit.barber.databinding.CardBarberBinding
import org.wit.barber.main.MainApp
import org.wit.barber.models.BarberModel

class BarberListActivity : AppCompatActivity() {

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
        binding.recyclerView.adapter = BarberAdapter(app.barbers)
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
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.barbers.size)
            }
        }
}

class BarberAdapter constructor(private var barbers: List<BarberModel>) :
    RecyclerView.Adapter<BarberAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardBarberBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val barber = barbers[holder.adapterPosition]
        holder.bind(barber)
    }

    override fun getItemCount(): Int = barbers.size

    class MainHolder(private val binding: CardBarberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(barber: BarberModel) {
            binding.barberTitle.text = barber.title
            binding.barberDescription.text = barber.description
        }
    }
}
