package com.example.gastos.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gastos.room.GastoModel

@Database(entities = [GastoModel::class], version = 2)
abstract class RoomDabase : RoomDatabase() {
  abstract fun GastoDao(): GastoDao

  companion object {
    // Singleton prevents multiple instances of database opening at the
    // same time.
    @Volatile
    private var INSTANCE: RoomDabase? = null

    fun getDatabase(context: Context): RoomDabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          RoomDabase::class.java,
          "bdGastos"
        ).fallbackToDestructiveMigration().build()
        INSTANCE = instance
        return instance
      }
    }
  }

}