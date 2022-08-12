package com.study.providerb

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.providerb.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val contentResolverHelper = ContentResolverHelper(this)
        val sdf = SimpleDateFormat("HH:mm:ss")

        // A앱 리스트 불러오기 - Log 출력
        binding.btnLog.setOnClickListener {

            // RoomDatabase 모두 불러오기
            contentResolverHelper.getAllItems()
        }

        // A앱 Item 추가
        binding.btnAdd.setOnClickListener {

            // RoomDatabase Insert
            contentResolverHelper.insertItem("*외부*", "Content - ${sdf.format(Date())}")

            Toast.makeText(this, "Add!", Toast.LENGTH_SHORT).show()
        }

        // A앱 커스텀 메서드 호출
        binding.btnCustomMethod.setOnClickListener {

            // 커스텀 메서드 호출
            val id = contentResolverHelper.customMethodGetId()
            Toast.makeText(this, "id : $id", Toast.LENGTH_SHORT).show()
        }
    }
}