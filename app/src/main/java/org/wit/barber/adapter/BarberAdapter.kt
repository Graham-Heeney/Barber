package org.wit.barber.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.barber.databinding.CardBarberBinding
import org.wit.barber.models.BarberModel

interface BarberListener {
    fun onBarberClick(barber: BarberModel)
}

class BarberAdapter(
    private var barbers: List<BarberModel>,
    private val listener: BarberListener
) : RecyclerView.Adapter<BarberAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardBarberBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val barber = barbers[holder.adapterPosition]
        holder.bind(barber, listener)
    }

    override fun getItemCount(): Int = barbers.size

    class MainHolder(private val binding: CardBarberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(barber: BarberModel, listener: BarberListener) {
            binding.barberTitle.text = barber.title
            binding.barberDescription.text = barber.description
            binding.root.setOnClickListener { listener.onBarberClick(barber) }
        }
    }
}
