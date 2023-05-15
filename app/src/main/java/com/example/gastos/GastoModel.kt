package com.example.gastos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "gasto")
data class GastoModel(
  @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "name")  val name: String,
  @ColumnInfo(name = "date")  val date: String,
  @ColumnInfo(name = "amount")  val amount: Int,
  @ColumnInfo(name = "state")  val state: String,
  )