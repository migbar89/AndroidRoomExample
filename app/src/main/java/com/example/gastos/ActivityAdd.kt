package com.example.gastos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gastos.databinding.ActivityAddBinding
import com.example.gastos.viewModel.GastoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ActivityAdd : AppCompatActivity() {
  private lateinit var binding: ActivityAddBinding
  private lateinit var viewModel: GastoViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GastoViewModel::class.java)

    binding.ivSave.setOnClickListener {
      GlobalScope.launch {
        Save()
      }
    }
  }


  suspend fun Save() {
    var newGasto = GastoModel(0,
      binding.etName.text.toString(), binding.etFecha.text.toString(),
      binding.etAmount.text.toString().toInt(),
      "Pending"
    )
    viewModel.insertProduct(newGasto)

    this.finish()

  }


}