package com.example.gastos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gastos.databinding.ActivityMainBinding
import com.example.gastos.room.RoomDabase
import com.example.gastos.viewModel.GastoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: GastoViewModel
  private lateinit var adapterGastos: GastoAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    binding.rvRecycler.layoutManager = LinearLayoutManager(this)
    viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GastoViewModel::class.java)
    observeEvents()


    val toolbar = binding.toolbar
    setSupportActionBar(toolbar)
  }
  private fun observeEvents() {
    viewModel.listGastos.observe(this, Observer { list ->
      list?.let {
        adapterGastos = GastoAdapter(it)
        binding.rvRecycler.adapter = adapterGastos
        adapterGastos.notifyDataSetChanged()
      }
    })

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

  override fun onRestart() {
    super.onRestart()
  }


}