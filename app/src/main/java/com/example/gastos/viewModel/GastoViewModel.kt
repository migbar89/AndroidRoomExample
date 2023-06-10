package com.example.gastos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gastos.room.GastoModel
import com.example.gastos.repository.GastoRepository
import com.example.gastos.room.RoomDabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GastoViewModel(application: Application) : AndroidViewModel(application) {
  val listGastos  : MutableLiveData<List<GastoModel>> =MutableLiveData()
  val repository : GastoRepository

  init {
    val gastoDao = RoomDabase.getDatabase(application).GastoDao()
    repository = GastoRepository(gastoDao)
    //listGastos = repository.ObtenerTodosGastos()
    GetGastoFirestore()
  }
  fun insertGasto(gasto: GastoModel) =
    viewModelScope.launch(Dispatchers.IO) { repository.guardarGasto(gasto) }

  fun updateGasto(gasto: GastoModel) =
    viewModelScope.launch(Dispatchers.IO) { repository.updateGasto(gasto) }

  fun deleteGasto(gasto: GastoModel) =
    viewModelScope.launch(Dispatchers.IO) { repository.deleteGasto(gasto) }
  fun GetGastoFirestore(){
    viewModelScope.launch(Dispatchers.IO) {
      var lista = repository.ObtenerTodosGastosFirestore()
      listGastos.postValue(lista)
    }
  }
}