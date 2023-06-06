package com.example.gastos.api

import com.example.gastos.room.GastoModel
import com.google.gson.annotations.SerializedName

 data class GastosResponseSave(
   @SerializedName("status") var status: String,
   @SerializedName("error") var error: Boolean,
   @SerializedName("errorMessage") var errorMessage: String,
   @SerializedName("data") var datos: Int
)


