package com.example.recognizer

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.core.content.ContextCompat
import java.net.URI

fun Context.isPermissionGranted (permission : String) : Boolean {
    return ContextCompat.checkSelfPermission(this , permission) == PackageManager.PERMISSION_GRANTED
}

inline fun Context.cameraPermissionRequest(crossinline positive : () -> Unit){
    AlertDialog.Builder(this)
        .setTitle("Camera Permission Required")
        .setMessage("Your camera permission is required to scan QR code..")
        .setPositiveButton("Allow Camera"){ dialog , which ->
            positive.invoke()
        }
        .setNegativeButton("Deny"){ dialog , which->

        }.show()
}

fun Context.onPermissionSetting(){
    Intent(ACTION_APPLICATION_DETAILS_SETTINGS).also{
        val uri : Uri = Uri.fromParts("package" , packageName , null)
        it.data = uri
        startActivity(it)

    }
}