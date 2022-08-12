package com.study.providerb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runProvider()
    }

    private fun runProvider() {

        val contentResolverHelper = ContentResolverHelper(this)

        val sdf = SimpleDateFormat("HH:mm:ss")

        // RoomDatabase Insert
        contentResolverHelper.insertItem("외부", "Content - ${sdf.format(Date())}")

        // RoomDatabase 모두 불러오기
        contentResolverHelper.getAllItems()

        // 커스텀 메서드 호출
        contentResolverHelper.customMethodGetId()
    }
}