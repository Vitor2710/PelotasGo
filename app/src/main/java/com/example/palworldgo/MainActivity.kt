package com.example.palworldgo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.webkit.WebView
import android.webkit.WebViewClient

// Cuida da função do GPS no mapa
open class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var tvGpsLocation: TextView
    private lateinit var locationManager: LocationManager
    private lateinit var imageView: ImageView
    private lateinit var webView: WebView
    private lateinit var GPSButton: ImageButton
    private lateinit var inventorybutton: Button
    private lateinit var Menubutton: ImageButton
    private val locationPermissionCode = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Palworld Go"
        Menubutton = findViewById(R.id.mbutton)
        webView = findViewById(R.id.webView)
        imageView = findViewById(R.id.Vitor2710)
        tvGpsLocation = findViewById(R.id.local)
        GPSButton = findViewById(R.id.GPSButton)
        inventorybutton = findViewById(R.id.inventorybutton)
        webView.addJavascriptInterface(WebAppInterface(this), "Android")
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                iniciar()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webView.loadUrl("file:///android_asset/openstreetmap.html")

        Menubutton.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        inventorybutton.setOnClickListener {
            val intent = Intent(this, Inventario::class.java)
            startActivity(intent)
        }

        GPSButton.setOnClickListener {
            updateLocation()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        } else {
            setupLocationManager()
        }
    }

    private fun setupLocationManager() {
        try {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
            } else {
                Toast.makeText(this, "Permissão de localização não concedida", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SecurityException) {
            Toast.makeText(this, "Erro de segurança: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissão Concedida", Toast.LENGTH_SHORT).show()
                setupLocationManager()
            } else {
                Toast.makeText(this, "Permissão Negada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun iniciar() {
        val parent = GPSButton.parent as? ViewGroup
        if (SharedVariables.taon) {
            parent?.removeView(GPSButton)
        }
        webView.evaluateJavascript("setDraggingState(${SharedVariables.taon});") { result ->
            Log.d("WebView", "JavaScript result: $result")
        }
    }

    private fun updateLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                onLocationChanged(location)
            } else {
                Toast.makeText(this, "Localização não disponível", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Permissão de localização não concedida", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onLocationChanged(location: Location) {
        try {
            runOnUiThread {
                tvGpsLocation.text = "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                webView.loadUrl("javascript:moveMapTo(${location.latitude}, ${location.longitude});")
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao atualizar localização: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
