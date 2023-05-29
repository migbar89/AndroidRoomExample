package com.example.gastos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.gastos.GastoModel
import com.example.gastos.room.GastoDao


class GastoRepository constructor(
  private val GastoDao: GastoDao
)
{
  fun ObtenerTodosGastos(): LiveData<List<GastoModel>> = GastoDao.obtenerGasto().asLiveData()

  suspend fun guardarGasto(gasto: GastoModel){
    GastoDao.guardarGasto(gasto)
  }

  //Create Funcion to Delete
}