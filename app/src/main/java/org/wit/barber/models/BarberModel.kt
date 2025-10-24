package org.wit.barber.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BarberModel(
    var id: Long = 0,
    var title: String = "",
    var description: String = ""
) : Parcelable
