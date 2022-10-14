package br.com.virtuallclothe.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray(): ByteArray {

    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, stream)

    if(stream.toByteArray().size > 500000){
        return stream.compressByteArray()
    }
    return stream.toByteArray()

}

fun ByteArray.toBitmap(): Bitmap{
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}

fun ByteArrayOutputStream.compressByteArray(): ByteArray {
    val bitmap: Bitmap = this.toByteArray().toBitmap()
    val resized: Bitmap = Bitmap.createScaledBitmap(bitmap, (bitmap.width*0.8).toInt(), (bitmap.height*0.8).toInt(), true)
    resized.compress(Bitmap.CompressFormat.PNG, 100, this)
    return this.toByteArray()
}
