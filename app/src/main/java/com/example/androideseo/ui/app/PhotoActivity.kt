package com.example.androideseo.ui.app

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R
import com.example.androideseo.databinding.ActivityPhotoBinding
import com.example.androideseo.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream
import java.net.URI


class PhotoActivity : AppCompatActivity() {


    // Probleme ligne 114 avec l'utilisation du @Multipart

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null

    private lateinit var binding: ActivityPhotoBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, PhotoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.captureBtn?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    openCamera()
                }
            }
            else{
                openCamera()
            }
        }

        supportActionBar?.apply {
            setTitle(R.string.Photo)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    openCamera()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            binding.imageView.setImageURI(image_uri)

            binding.envoiphoto?.setOnClickListener {
                Toast.makeText(
                        this@PhotoActivity, "Fonctionnalité en cours de creation (probleme coté client)", Toast.LENGTH_LONG).show()

                CoroutineScope(Dispatchers.IO).launch {
                    runCatching {

                        // --------- Solution non fonctionnel -------------------------
                        val image = File(URI(image_uri.toString()))
                        val res = ApiService.instance.upload(image)
                        runOnUiThread {
                            Toast.makeText(
                                this@PhotoActivity, "envoi en cours",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(MainActivity.getStartIntent(this@PhotoActivity))
                        }
                    }
                }

            }
        }
    }

}