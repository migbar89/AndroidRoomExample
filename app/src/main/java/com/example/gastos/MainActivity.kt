package com.example.gastos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gastos.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var adapterGastos: GastoAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    InitListGastos()
    binding.rvRecycler.layoutManager = LinearLayoutManager(this)
    adapterGastos = GastoAdapter(listGastos)
    binding.rvRecycler.adapter = adapterGastos
    val toolbar = binding.toolbar
    setSupportActionBar(toolbar)
  }

  private fun InitListGastos() {
    var date = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES")).format(Date())
    listGastos = arrayListOf(
      GastoModel("Super Mercado", date, 100, "Cancel"),
      GastoModel("Transporte", date, 300, "Pending"),
      GastoModel("Alquiler", date, 2500, "Cancel"),
      GastoModel("Super Mercado1", date, 100, "Cancel"),
      GastoModel("Transporte2", date, 300, "Pending"),
      GastoModel("Alquiler3", date, 2500, "Cancel")
    )
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_add_gasto) {
      var intent = Intent(this, ActivityAdd::class.java)
      startActivity(intent)
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_gasto, menu)
    return super.onCreateOptionsMenu(menu)
  }

  companion object {
    lateinit var listGastos: ArrayList<GastoModel>

  }

  override fun onRestart() {
    super.onRestart()
    adapterGastos.notifyDataSetChanged()
  }
}