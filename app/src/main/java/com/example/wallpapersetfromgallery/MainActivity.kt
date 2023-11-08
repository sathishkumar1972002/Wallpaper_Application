package com.example.wallpapersetfromgallery

import android.app.DownloadManager
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.core.content.getSystemService
import java.lang.Math.random

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageUrl ="https://www.gstatic.com/webp/gallery/1.jpg"
        val setWallpaper = findViewById<Button>(R.id.setWallpaperButton)
       // Picasso.get().load(imageUrl)


        setWallpaper.setOnClickListener {
            var filename ="ImageTest"+ (Random.nextInt(1000).toString())
            val request = DownloadManager.Request(Uri.parse(imageUrl))
                .setTitle(filename)
                .setDescription("Downloading")
                .setDestinationInExternalFilesDir(applicationContext,Environment.DIRECTORY_DOWNLOADS,"image.jpg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)

            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
            val contentResolver = applicationContext.contentResolver
            MediaScannerConnection.scanFile(
                applicationContext,
                arrayOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/my_image.jpg"),
                null,
                null
            )
        }
    }
}