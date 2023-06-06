package com.example.gastos.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gastos.api.GastoService
import com.example.gastos.room.GastoModel
import com.example.gastos.repository.GastoRepository
import com.example.gastos.room.RoomDabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GastoViewModel(application: Application) : AndroidViewModel(application) {
  val listGastos  : MutableLiveData<List<GastoModel>?> =MutableLiveData()
  val repository : GastoRepository


  init {
    val gastoDao = RoomDabase.getDatabase(application).GastoDao()
    repository = GastoRepository(gastoDao)
    //listGastos = repository.ObtenerTodosGastos()
    viewModelScope.launch(Dispatchers.IO) {
      var data = GastoService.GetAllGastos()
      listGastos.postValue(data)
    }

    Log.e("Iniciando ViewModel", "Iniciando ViewModel")
  }
  fun insertGasto(gasto: GastoModel) =
    viewModelScope.launch(Dispatchers.IO) { repository.guardarGasto(gasto) }
  fun insertGastoApi(gasto: GastoModel) = viewModelScope.launch(Dispatchers.IO) { GastoService.SaveGastos(gasto) }

}
