package com.asianaidt.edu.librarypractice_20220518

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    }

}