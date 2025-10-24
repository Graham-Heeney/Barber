package org.wit.barber.models

interface BarberStore {
    fun findAll(): MutableList<BarberModel>
    fun findOne(id: Long): BarberModel?
    fun create(barber: BarberModel)
    fun update(barber: BarberModel)
    fun delete(barber: BarberModel)
}
