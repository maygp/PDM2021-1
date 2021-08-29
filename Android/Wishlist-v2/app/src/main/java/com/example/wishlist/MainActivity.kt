package com.example.wishlist

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvDesejos: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: ArrayList<Desejo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lista = arrayListOf()

        this.rvDesejos = findViewById(R.id.rvDesejos)
        this.fabAdd = findViewById(R.id.fabAdd)

        this.rvDesejos.adapter = ListaDesejosAdapter(this.lista)

        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val desejo = it.data?.getSerializableExtra("DESEJO") as Desejo
                this.lista.add(desejo)
                (this.rvDesejos.adapter as ListaDesejosAdapter).notifyDataSetChanged()
            }
        }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            resultForm.launch(intent)
        }

        (this.rvDesejos.adapter as ListaDesejosAdapter).listener = OnItemClick()
        (this.rvDesejos.adapter as ListaDesejosAdapter).listener = OnItemLongClick()
    }

        inner class OnItemClick: ItemClickListenerRecycleView{
            override fun onItemClick(position: Int) {
                val desejo = this@MainActivity.lista[position]
                Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()
            }
        }

        inner class OnItemLongClick: ItemClickListenerRecycleView {
            override fun onItemClick(position: Int) {
            var desejo = lista[position]
                excluirDialog(this@MainActivity, position)
            }
        }

        fun excluirDialog(activity: Activity, position: Int){
            var alertDialog = AlertDialog.Builder(this)
            var desejo = this@MainActivity.lista[position]

            alertDialog.setTitle("Excluir")
            alertDialog.setMessage("Excluir " + desejo.descricao + " da lista?")
0
            alertDialog.setPositiveButton("Excluir") { _, _ ->
                this@MainActivity.lista.removeAt(position)
                (this@MainActivity.rvDesejos.adapter as ListaDesejosAdapter).notifyDataSetChanged()
                Toast.makeText(this@MainActivity, "Excluído!", Toast.LENGTH_LONG).show()
            }
            alertDialog.setNegativeButton("Cancelar", null)

            alertDialog.create().show()
        }
}