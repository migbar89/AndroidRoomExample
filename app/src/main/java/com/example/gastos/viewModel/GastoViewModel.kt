package com.example.gastos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gastos.GastoModel
import com.example.gastos.repository.GastoRepository
import com.example.gastos.room.RoomDabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GastoViewModel(application: Application) : AndroidViewModel(application) {
  val listGastos  : LiveData<List<GastoModel>>
  val repository : GastoRepository

  init {
    val gastoDao = RoomDabase.getDatabase(application).GastoDao()
    repository = GastoRepository(gastoDao)
    listGastos = repository.ObtenerTodosGastos()
  }
  fun insertProduct(gasto: GastoModel) =
    viewModelScope.launch(Dispatchers.IO) { repository.guardarGasto(gasto) }
}