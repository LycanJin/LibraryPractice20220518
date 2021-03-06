package com.asianaidt.edu.librarypractice_20220518

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setupValues()
    }

    fun setupEvents() {
        img_profile.setOnClickListener {
            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)

        }

        txt_phone.setOnClickListener {

            val permissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
                    val myUri = Uri.parse("tel:0105556666")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(this@MainActivity, "권한이 없어서 통화가 불가능 합니다.", Toast.LENGTH_LONG).show()
                }

            }

            TedPermission.create()
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()

        }

    }

    fun setupValues() {
        // https 만 가능
        Glide.with(this).load("https://i.discogs.com/NHAk7rYgJrK1QueMfGzzw9pdGoyFx6fZUUW7xBYqlB4/rs:fit/g:sm/q:90/h:315/w:306/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9BLTc5Mzg3/NC0xMTc2OTA3MTAy/LmpwZWc.jpeg").into(img_web)
    }

}