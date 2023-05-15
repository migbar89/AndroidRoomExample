package com.example.gastos.room

import androidx.room.*
import com.example.gastos.GastoModel

@Dao
interface GastoDao {
  @Query("SELECT * from gasto")
  fun obtenerGasto(): MutableList<GastoModel>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun guardarGasto(gastoModel: GastoModel)

  @Update
  fun actualizarGasto(gastoModel: GastoModel)

  @Query("DELETE FROM gasto")
  suspend fun eliminarTodosGastos()

  @Delete
  suspend fun eliminarGasto(gastoModel: GastoModel)
}