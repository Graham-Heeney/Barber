package org.wit.barber.models

interface BarberStore {
    fun findAll(): List<BarberModel>
    fun create(barber: BarberModel)
}













