package com.eee.contentproviderexternalclient

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testUri: Uri = Uri.parse("content://com.eee.provider")

        val cursor = contentResolver.query(testUri, null, null, null)
        CommonUtils.log("@# cursor : $cursor")
        CommonUtils.log("@# cursor2 : ${cursor?.moveToNext()}")
        CommonUtils.log("@# GET TYPE : ${contentResolver.getType(testUri)}")
    }
}