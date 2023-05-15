package com.example.gastos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "gasto")
data class GastoModel(
  @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "name") var name: String,
  @ColumnInfo(name = "date") var date: String,
  @ColumnInfo(name = "amount") var amount: Int,
  @ColumnInfo(name = "state")  val state: String,
  )