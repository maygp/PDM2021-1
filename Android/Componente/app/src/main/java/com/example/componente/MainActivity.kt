package com.example.componente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btEnviar : Button
    private lateinit var sbNota : SeekBar
    private lateinit var tvNota : TextView
    private lateinit var tvTitulo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btEnviar = findViewById(R.id.buttonEnviar)
        this.sbNota = findViewById(R.id.seekBarNota)
        this.tvNota = findViewById(R.id.textViewNota)
        this.tvTitulo = findViewById(R.id.textViewTitulo)

        this.btEnviar.setOnClickListener(OnClickBt())
        this.sbNota.setOnSeekBarChangeListener(ChangeSeekBar())

        this.tvNota.text = this.sbNota.progress.toString()
    }

    inner class OnClickBt : View.OnClickListener {
        override fun onClick(v: View?) {
            verificaNota(sbNota.progress)
        }
    }

    inner class ChangeSeekBar : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this@MainActivity.tvNota.text = progress.toString()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

        fun verificaNota(nota: Int){
        if (nota < 50) this@MainActivity.tvTitulo.text = "Poxa, vou tentar melhorar!"
        if (nota in 50..99) this@MainActivity.tvTitulo.text = "Obrigada!"
        if (nota == 100) this@MainActivity.tvTitulo.text = "Yay! Muito obrigada!"
    }
}