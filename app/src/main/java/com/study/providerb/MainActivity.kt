package com.study.providerb

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val REQ_PERMISSIONS_CODE = 1312
    private val PERMISSIONS_STORAGE = arrayOf(
        "com.app.first.READ_DATABASE",
        "com.app.first.WRITE_DATABASE"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //requestPermissions(PERMISSIONS_STORAGE, REQ_PERMISSIONS_CODE)

        runProvider()
    }

    private fun runProvider() {

        val contentResolverHelper = ContentResolverHelper(this)
        contentResolverHelper.insertCompanyTMRecord("외부1", "외부1")
        contentResolverHelper.allItems()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Log.v(">>>", "ob-1")
        if (requestCode == REQ_PERMISSIONS_CODE) {

            Log.v(">>>", "ob-2")
            var granted = true
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    granted = false
                    break
                }
            }

            Log.v(">>>", "ob-3 : $granted")
            if (granted) {

                runProvider()

            } else {


            }
        }
    }
}