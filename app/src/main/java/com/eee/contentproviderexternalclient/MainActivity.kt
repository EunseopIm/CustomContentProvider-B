package com.eee.contentproviderexternalclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contentResolverHelper = ContentResolverHelper(this)
        //contentResolverHelper.insertCompanyTMRecord("제목1", "내용1")

        contentResolverHelper.allItems()
    }
}