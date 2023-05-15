package com.example.gastos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gastos.databinding.ActivityAddBinding
import com.example.gastos.databinding.ActivityMainBinding

class ActivityAdd : AppCompatActivity() {
  private lateinit var binding: ActivityAddBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    binding.ivSave.setOnClickListener {
    Save()
    }
  }
  fun Save(){
    var newGasto = GastoModel(
      binding.etName.text.toString()
      , binding.etFecha.text.toString(),
      binding.etAmount.text.toString().toInt(),
      "Pending"
    )
    MainActivity.listGastos.add(newGasto)
    this.finish()

  }



}