package com.latihanimpilcitintent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.latihanimplicitintent.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button1)
        button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL
            )
        } else {
            makePhoneCall()
        }
    }

    private fun makePhoneCall() {
        val phoneNumber = "tel:085156633128" // Ganti dengan nomor yang sesuai
        val callIntent = Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber))
        try {
            startActivity(callIntent)
        } catch (e: SecurityException) {
            e.printStackTrace()
            Toast.makeText(this, "Permission Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CALL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Jika izin diberikan, lakukan panggilan telepon
                makePhoneCall()
            } else {
                // Jika izin ditolak, beri tahu pengguna
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val REQUEST_CALL = 1
    }
}
