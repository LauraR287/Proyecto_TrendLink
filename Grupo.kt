package com.lrincon.pruebas_proyecto

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase

data class Grupo(val id: String, val nombre: String, val descripcion: String) {}

class GrupoFunctions {
    private val database = FirebaseDatabase.getInstance().reference.child("Grupos")
    fun crearGrupo(grupo: Grupo): Task<Void> {
        val grupoId = database.push().key
        return if (grupoId != null) {
            val grupoCreado = Grupo(grupoId, grupo.nombre, grupo.descripcion)
            database.child(grupoId).setValue(grupoCreado)
        } else {
            Tasks.forException(Exception("No se pudo crear el grupo"))
        }

    }
}