package com.example.deviceinfo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvInfo : TextView
    private lateinit var tvInfo2 : TextView
    private lateinit var btSobre : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvInfo = findViewById(R.id.tvInfo)
        this.tvInfo2 = findViewById(R.id.tvInfo2)
        this.btSobre = findViewById(R.id.btSobre)

        this.btSobre.setOnClickListener(OnClickBt())
    }

    inner class OnClickBt : View.OnClickListener {
        override fun onClick(v: View?){
            this@MainActivity.tvInfo.text = "Marca do dispositivo: " + Build.BRAND
            this@MainActivity.tvInfo2.text = "Build.BRAND exibe a marca à qual o produto está associado."
        }
    }
}