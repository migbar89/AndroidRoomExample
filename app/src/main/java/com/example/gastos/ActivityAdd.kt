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
    binding.ivSave.setOnClickListener {
      GlobalScope.launch {
        Save()

      }
    }
  }
  suspend fun Save(){
    var newGasto = GastoModel(0,
      binding.etName.text.toString()
      , binding.etFecha.text.toString(),
      binding.etAmount.text.toString().toInt(),
      "Pending"
    )
    MainActivity.dataBaseInstance?.GastoDao()?.guardarGasto(newGasto)
    //MainActivity.listGastos.add(newGasto)
    this.finish()

  }



}