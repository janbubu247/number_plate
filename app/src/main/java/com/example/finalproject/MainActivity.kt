package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R.id.idBtnDBA
import com.example.finalproject.R.id.idBtnDBS

class MainActivity : AppCompatActivity() {
    private var captureBtn: Button? = null
    private var buttonDBS: Button? = null
    private var buttonDBA: Button? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonDBS = findViewById<Button>(R.id.idBtnDBS)
        buttonDBA = findViewById<Button>(R.id.idBtnDBA)
        captureBtn = findViewById<Button>(R.id.idBtnCapture)
        captureBtn?.setOnClickListener(View.OnClickListener {
            val i = Intent(
                this@MainActivity,
                ScannerActivity::class.java
            )
            startActivity(i)

        })
        buttonDBA?.setOnClickListener(View.OnClickListener {
            val i = Intent(
                this@MainActivity,
                ActivityDBA::class.java
            )
            startActivity(i)
        })
        buttonDBS?.setOnClickListener(View.OnClickListener {
            val i = Intent(
                this@MainActivity,
                ActivityDBS::class.java
            )
            startActivity(i)
        })
    }
}
