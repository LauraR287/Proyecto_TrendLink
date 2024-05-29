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

    // La función eliminarGrupo elimina el grupo seleccionado
    fun eliminarGrupo(database: DatabaseReference, idGrupo: String) {
        database.child(idGrupo).removeValue()
    }

}