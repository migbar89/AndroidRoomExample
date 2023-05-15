package com.example.gastos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gastos.databinding.ActivityMainBinding
import com.example.gastos.room.RoomDabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var adapterGastos: GastoAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    dataBaseInstance = RoomDabase.getDatabase(this)

    binding.rvRecycler.layoutManager = LinearLayoutManager(this)
    cargarDatos()

    val toolbar = binding.toolbar
    setSupportActionBar(toolbar)


  }

  //  private fun InitListGastos() {
//    var date = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES")).format(Date())
//    listGastos = arrayListOf(
//      GastoModel("Super Mercado", date, 100, "Cancel"),
//      GastoModel("Transporte", date, 300, "Pending"),
//      GastoModel("Alquiler", date, 2500, "Cancel"),
//      GastoModel("Super Mercado1", date, 100, "Cancel"),
//      GastoModel("Transporte2", date, 300, "Pending"),
//      GastoModel("Alquiler3", date, 2500, "Cancel")
//    )
//  }
  fun cargarDatos() {


    GlobalScope.launch(Dispatchers.IO) {
      listGastos = dataBaseInstance!!.GastoDao().obtenerGasto()

      withContext(Dispatchers.Main) {
        adapterGastos = listGastos?.let { GastoAdapter(it) }!!
        binding.rvRecycler.adapter = adapterGastos
        adapterGastos.notifyDataSetChanged()

      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_add_gasto) {
      var intent = Intent(this, ActivityAdd::class.java)
      isEdit =false;
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
    var listGastos: MutableList<GastoModel> = mutableListOf()
    var dataBaseInstance: RoomDabase? = null
    var itemGastoSelect :GastoModel ?=null
    var isEdit = false
  }

  override fun onRestart() {
    cargarDatos()
    super.onRestart()
  }


}