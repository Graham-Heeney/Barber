package org.wit.barber.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.barber.databinding.CardBarberBinding
import org.wit.barber.models.BarberModel
import android.widget.Filter
import android.widget.Filterable
import android.widget.Filter.FilterResults

interface BarberListener {
    fun onBarberClick(barber: BarberModel)
}

class BarberAdapter(
    private var barbers: MutableList<BarberModel>,
    private val listener: BarberListener
) : RecyclerView.Adapter<BarberAdapter.MainHolder>(), Filterable {

    private var barbersFull: List<BarberModel> = barbers.toList()

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

    fun updateData(newBarbers: MutableList<BarberModel>) {
        barbers = newBarbers
        barbersFull = newBarbers.toList()
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<BarberModel>()

                if (constraint.isNullOrBlank()) {
                    filteredList.addAll(barbersFull)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()
                    for (barber in barbersFull) {
                        if (barber.title.lowercase().contains(filterPattern)) {
                            filteredList.add(barber)
                        }
                    }
                }

                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                barbers = (results?.values as List<BarberModel>).toMutableList()
                notifyDataSetChanged()
            }
        }
    }


    class MainHolder(private val binding: CardBarberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(barber: BarberModel, listener: BarberListener) {
            binding.barberTitle.text = barber.title
            binding.barberDescription.text = barber.description
            binding.root.setOnClickListener { listener.onBarberClick(barber) }
        }
    }
}
