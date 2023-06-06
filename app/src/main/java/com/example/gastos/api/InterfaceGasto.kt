package com.example.gastos.api

import com.example.gastos.room.GastoModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InterfaceGasto {
  @GET("api/Gastos/GetAll")
  suspend  fun GetAll(): Response<GastosResponse?>?

  @POST("api/Gastos/Save")
  suspend fun Save(@Body gasto: GastoModel): Response<GastosResponseSave>
}