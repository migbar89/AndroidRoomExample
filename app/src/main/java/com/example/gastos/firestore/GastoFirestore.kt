package com.example.gastos.firestore

import android.util.Log
import com.example.gastos.room.GastoModel
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

object GastoFirestore {
    suspend fun getGastos(): List<GastoModel> = coroutineScope {
        val db = Firebase.firestore
        val listaGasto = mutableListOf<GastoModel>()

        try {
            val querySnapshot = db.collection("Gastos").get().await()
            listaGasto.addAll(obtenerListaGastos(querySnapshot))
        } catch (e: Exception) {
            Log.e("Error", "Error fetching documents", e)
        }
        listaGasto
    }

    suspend fun createGasto(gastoModel: GastoModel) = coroutineScope {
        val db = Firebase.firestore
        db.collection("Gastos").add(gastoModel).await()
    }

    suspend fun updateGasto(gastoModel: GastoModel) = coroutineScope {
        val db = Firebase.firestore
        val newData = hashMapOf<String, Any>(
            "name" to gastoModel.name,
            "date" to gastoModel.date,
            "amount" to gastoModel.amount,
            "state" to gastoModel.state,
        )
        db.collection("Gastos").document(gastoModel.idFirestore!!).update(newData).await()
    }

    suspend fun deleteGasto(gastoModel: GastoModel) = coroutineScope {
        val db = Firebase.firestore
        db.collection("Gastos").document(gastoModel.idFirestore!!).delete()

    }

    fun obtenerListaGastos(querySnapshot: QuerySnapshot): List<GastoModel> {
        val listaGastos = mutableListOf<GastoModel>()

        for (document in querySnapshot.documents) {
            val gastoData = document.data
            val gastoModel = gastoData?.let { convertirAGastoModel(it, document.id) }

            listaGastos.add(gastoModel!!)
        }

        return listaGastos
    }

    fun convertirAGastoModel(gastoData: Map<String, Any>, id: String): GastoModel {

        val name = gastoData["name"] as String
        val date = gastoData["date"] as String
        val amount = gastoData["amount"] as Long
        val state = gastoData["state"] as String

        return GastoModel(0, name, date, amount, state, id)
    }
}