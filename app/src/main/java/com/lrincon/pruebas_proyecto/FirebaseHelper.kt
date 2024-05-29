// MODEL
// Funciones para gestionar grupos

package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

data class Grupo(
    var descripcion: String = "",
    var idGrupo: String = "",
    var nombre: String = "",
    var integrantes: String = ""
)

data class Usuario(
    val id: String = "",
    val nombre: String = ""
)
class FirebaseHelper {

    // La función crearGrupo agrega un nuevo grupo a la bandeja de mensajes
    fun crearGrupo(database: DatabaseReference, nombre: String, descripcion: String, integrantes: String) {
        val grupoId = database.push().key

        if (grupoId != null) {
            val grupo = Grupo(descripcion, grupoId, nombre, integrantes)
            database.child(grupoId).setValue(grupo)
        }
    }

    // La función infoGrupo trae toda la información del grupo seleccionado
    fun infoGrupo(database: DatabaseReference, grupoId: String, callback: (Grupo?) -> Unit) {
        database.child(grupoId).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val grupo = dataSnapshot.getValue(Grupo::class.java)
                callback(grupo)
            } else {
                callback(null)
            }
        }
    }

    // La función consultarGrupo muestra los grupos activos
    fun consultarGrupo(database: DatabaseReference, callback: (List<Grupo>) -> Unit) {
        database.get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val grupoList = mutableListOf<Grupo>()
                for (snapshot in dataSnapshot.children) {
                    val grupo = snapshot.getValue(Grupo::class.java)
                    if (grupo != null) {
                        grupoList.add(grupo)
                    }
                }
                callback(grupoList)
            } else {
                callback(emptyList())
            }
        }
    }

    fun consultarUsuarios(database: DatabaseReference, callback: (List<Usuario>) -> Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val usuarioList = mutableListOf<Usuario>()
                for (snapshot in dataSnapshot.children) {
                    val usuario = snapshot.getValue(Usuario::class.java)
                    if (usuario != null) {
                        usuarioList.add(usuario)
                    }
                }
                callback(usuarioList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors
            }
        })
    }
}