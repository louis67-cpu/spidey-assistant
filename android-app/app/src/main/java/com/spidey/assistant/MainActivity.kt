package com.spidey.assistant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ask Android for permission to let Spidey appear over other apps
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        } else {
            startSpidey()
        }
    }

    override fun onResume() {
        super.onResume()

        if (Settings.canDrawOverlays(this)) {
            startSpidey()
        }
    }

    private fun startSpidey() {
        val intent = Intent(this, SpideyService::class.java)
        startForegroundService(intent)
    }
}
