package org.wit.barber.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.wit.barber.helpers.*
import timber.log.Timber.i
import java.util.*

val JSON_FILE = "barbers.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<BarberModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BarberJSONStore(private val context: Context) : BarberStore {

    var barbers = mutableListOf<BarberModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BarberModel> {
        return barbers
    }

    override fun findOne(id: Long): BarberModel? {
        val foundBarber: BarberModel? = barbers.find { b -> b.id == id }
        return foundBarber
    }

    override fun create(barber: BarberModel) {
        barber.id = generateRandomId()
        barbers.add(barber)
        serialize()
    }



    override fun delete(barber: BarberModel) {
        barbers.removeIf { it.id == barber.id }
        serialize()
    }

    override fun update(barber: BarberModel) {
        val foundBarber: BarberModel? = barbers.find { p -> p.id == barber.id }
        if (foundBarber != null) {
            foundBarber.title = barber.title
            foundBarber.description = barber.description
            foundBarber.image = barber.image
            foundBarber.lat = barber.lat
            foundBarber.lng = barber.lng
            foundBarber.zoom = barber.zoom
            serialize()
            logAll()
        }
    }


    internal fun logAll() {
        barbers.forEach { i("$it") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(barbers, listType)
        write(context, JSON_FILE, jsonString)
    }

    override fun findById(id:Long) : BarberModel? {
        val foundPlacemark: BarberModel? = barbers.find { it.id == id }
        return foundPlacemark
    }




    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        if (jsonString.isNotEmpty()) {
            barbers = Gson().fromJson(jsonString, listType)
        } else {
            barbers = mutableListOf()
        }
    }

}
