package com.example.myapplication.`object`

import android.content.Context
import android.content.Intent
import android.net.Uri

object Utils {

    fun openBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}