package com.example.gastos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.gastos.firestore.GastoFirestore
import com.example.gastos.firestore.GastoFirestore.getGastos
import com.example.gastos.firestore.GastoFirestore.createGasto
import com.example.gastos.room.GastoModel
import com.example.gastos.room.GastoDao


class GastoRepository constructor(
  private val GastoDao: GastoDao
)
{
  fun ObtenerTodosGastos(): LiveData<List<GastoModel>> = GastoDao.obtenerGasto().asLiveData()
  suspend fun ObtenerTodosGastosFirestore(): List<GastoModel> = getGastos()

  suspend fun guardarGasto(gasto: GastoModel){
    createGasto(gasto)
  }
  suspend fun updateGasto(gasto: GastoModel){
    GastoFirestore.updateGasto(gasto)
  }
  suspend fun deleteGasto(gasto: GastoModel){
    GastoFirestore.deleteGasto(gasto)
  }

}