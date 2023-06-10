package com.example.gastos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gastos.databinding.ActivityAddBinding
import com.example.gastos.room.GastoModel
import com.example.gastos.viewModel.GastoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ActivityAdd : AppCompatActivity() {
  private lateinit var binding: ActivityAddBinding
  private lateinit var viewModel: GastoViewModel
  var gastoSelect = MainActivity.gastoSelect
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GastoViewModel::class.java)

    if(MainActivity.isEdit){
      binding.etAmount.setText(gastoSelect.amount.toString())
      binding.etName.setText(gastoSelect.name)
      binding.etFecha.setText(gastoSelect.date)
    }

    binding.ivSave.setOnClickListener {
      GlobalScope.launch {
       if(MainActivity.isEdit)
       {
         Update()
       }
        else {
         Save()
        }

      }
    }
  }

  fun Update(){
    gastoSelect.amount = binding.etAmount.text.toString().toLong()
    gastoSelect.name = binding.etName.text.toString()
    gastoSelect.amount = binding.etAmount.text.toString().toLong()
    viewModel.updateGasto(gastoSelect)
    this.finish()

  }
  suspend fun Save() {
    var newGasto = GastoModel(0,
      binding.etName.text.toString(),
      binding.etFecha.text.toString(),
      binding.etAmount.text.toString().toLong(),
      "Pending",
      null
    )
    viewModel.insertGasto(newGasto)

    this.finish()

  }


}