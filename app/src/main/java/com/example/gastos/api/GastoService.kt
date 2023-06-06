package com.example.gastos.api

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.gastos.room.GastoModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GastoService {
  private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://9d23-186-77-207-142.ngrok.io/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  suspend fun GetAllGastos(): List<GastoModel>? {
    try {
      Log.e("Consulta Api1", "Consulta Api1")

      val response = getRetrofit().create(InterfaceGasto::class.java).GetAll()
      if (response!!.isSuccessful && response.code() == 200) {
        return response.body()?.datos
      }
    } catch (e: Exception) {
      Log.e("Error", e.message.toString())
    }
    return null
  }

  suspend fun SaveGastos(gasto: GastoModel): Int {
    try {
      Log.e("Save", "Consulta Api1")
      Log.e("Save a Guardar", gasto.toString())

      val response = getRetrofit().create(InterfaceGasto::class.java).Save(gasto)
      Log.e("Save Response", response.body().toString())

      if (response!!.isSuccessful && response.code() == 200) {
        Log.e("Save Response", response.body()!!.datos.toString())

        return response.body()!!.datos
      }
    } catch (e: Exception) {
      Log.e("Save Error1", e.message.toString())
      Log.e("Save Error2", e.cause.toString())
      Log.e("Save Error2", e.stackTrace.toString())
    }
    return 0
  }
}