package org.wit.barber.helpers

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import org.wit.barber.R


fun showImagePicker(
    context: Context,
    intentLauncher: ActivityResultLauncher<Intent>
) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(
        chooseFile,
        context.getString(R.string.select_barber_image)
    )
    intentLauncher.launch(chooseFile)
}
