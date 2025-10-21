package org.wit.barber.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BarberModel(
    var title: String = "",
    var description: String = ""
) : Parcelable
