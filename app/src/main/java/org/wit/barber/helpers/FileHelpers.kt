package org.wit.barber.helpers

import android.content.Context
import java.io.*

fun write(context: Context, fileName: String, data: String) {
    val fileOutputStream: FileOutputStream
    try {
        fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        fileOutputStream.write(data.toByteArray())
        fileOutputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun read(context: Context, fileName: String): String {
    var str = ""
    try {
        val fileInputStream = context.openFileInput(fileName)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder()
        var text: String?
        while (bufferedReader.readLine().also { text = it } != null) {
            stringBuilder.append(text)
        }
        str = stringBuilder.toString()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return str
}

fun exists(context: Context, fileName: String): Boolean {
    val file = context.getFileStreamPath(fileName)
    return file.exists()
}
