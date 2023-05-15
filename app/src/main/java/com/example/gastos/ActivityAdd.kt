package com.example.gastos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gastos.databinding.ActivityAddBinding
import com.example.gastos.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityAdd : AppCompatActivity() {
  private lateinit var binding: ActivityAddBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    binding.etAmount.setText(MainActivity.itemGastoSelect!!.amount.toString())
    binding.etFecha.setText(MainActivity.itemGastoSelect!!.date.toString())
    binding.etName.setText(MainActivity.itemGastoSelect!!.name.toString())


    binding.ivSave.setOnClickListener {
      GlobalScope.launch {
        if (MainActivity.isEdit) {
          Update()
        } else {
          Save()
        }
      }
    }
  }

  suspend fun Update() {
    MainActivity.itemGastoSelect?.name =   binding.etName.text.toString()
    MainActivity.itemGastoSelect?.amount =   binding.etAmount.text.toString().toInt()
    MainActivity.itemGastoSelect?.date =   binding.etFecha.text.toString()
    MainActivity.itemGastoSelect?.let { MainActivity.dataBaseInstance?.GastoDao()?.actualizarGasto (it) }
    finish()
  }

  suspend fun Save() {
    var newGasto = GastoModel(0,
      binding.etName.text.toString(), binding.etFecha.text.toString(),
      binding.etAmount.text.toString().toInt(),
      "Pending"
    )
    MainActivity.dataBaseInstance?.GastoDao()?.guardarGasto(newGasto)
    //MainActivity.listGastos.add(newGasto)
    this.finish()

  }


}