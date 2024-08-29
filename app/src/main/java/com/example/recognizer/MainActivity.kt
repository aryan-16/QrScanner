package com.example.recognizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.recognizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val cameraPermission = android.Manifest.permission.CAMERA
    private lateinit var binding : ActivityMainBinding

    private val requestPermissionLauncher  = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted->

        if(isGranted){
            //start scanner
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenScanner.setOnClickListener {
            requestCameraAndStartScanner()
        }
    }

    private fun requestCameraAndStartScanner(){
        if(isPermissionGranted(cameraPermission)){
            // start Scanner
        }
        else{
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        when{
            shouldShowRequestPermissionRationale(cameraPermission)->{
                cameraPermissionRequest {
                    openPermissionSetting()
                }
            }else->{
                requestPermissionLauncher.launch(cameraPermission)
            }
        }
    }
}