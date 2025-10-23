package org.wit.barber.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BarberMemStore : BarberStore {

    private val barbers = ArrayList<BarberModel>()

    override fun findAll(): List<BarberModel> {
        return barbers
    }

    override fun create(barber: BarberModel) {
        barber.id = getId().toString()
        barbers.add(barber)
        logAll()
    }

    override fun update(barber: BarberModel) {
        val foundBarber = barbers.find { b -> b.id == barber.id }
        if (foundBarber != null) {
            foundBarber.title = barber.title
            foundBarber.description = barber.description
            logAll()
        }
    }

    private fun logAll() {
        barbers.forEach { i("$it") }
    }
}
