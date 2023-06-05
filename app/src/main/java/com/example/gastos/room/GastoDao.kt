package com.example.gastos.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.gastos.room.GastoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GastoDao {
  @Query("SELECT * from gasto")
  fun obtenerGasto(): Flow<List<GastoModel>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun guardarGasto(gastoModel: GastoModel)

  @Update
  fun actualizarGasto(gastoModel: GastoModel)

  @Query("DELETE FROM gasto")
  suspend fun eliminarTodosGastos()

  @Delete
  suspend fun eliminarGasto(gastoModel: GastoModel)
}