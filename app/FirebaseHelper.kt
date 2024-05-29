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

    // La función editarGrupo actualiza los datos editados del grupo creado
    fun editarGrupo(database: DatabaseReference, idGrupo: String, nombre: String, descripcion: String) {
        val updates = mutableMapOf<String, Any>()

        if (!nombre.isNullOrEmpty()) {
            updates["nombre"] = nombre
        }

        if (!descripcion.isNullOrEmpty()) {
            updates["descripcion"] = descripcion
        }

        if (updates.isNotEmpty()) {
            database.child(idGrupo).updateChildren(updates)
        }
    }

    // La función eliminarGrupo elimina el grupo seleccionado
    fun eliminarGrupo(database: DatabaseReference, idGrupo: String) {
        database.child(idGrupo).removeValue()
    }
}