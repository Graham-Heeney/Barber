package org.wit.barber.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.barber.databinding.ActivityBarberListBinding
import org.wit.barber.main.MainApp

class BarberListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityBarberListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarberListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = BarberAdapter(app.barbers)
    }
}
