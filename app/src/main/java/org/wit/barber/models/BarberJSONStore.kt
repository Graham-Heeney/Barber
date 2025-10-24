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

    override fun update(barber: BarberModel) {
        val foundBarber = findOne(barber.id)
        if (foundBarber != null) {
            foundBarber.title = barber.title
            foundBarber.description = barber.description
        }
        serialize()
    }

    override fun delete(barber: BarberModel) {

    }


    internal fun logAll() {
        barbers.forEach { i("$it") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(barbers, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        barbers = Gson().fromJson(jsonString, listType)
    }
}
