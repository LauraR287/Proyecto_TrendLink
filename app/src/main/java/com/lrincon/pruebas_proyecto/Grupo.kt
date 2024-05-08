package com.lrincon.pruebas_proyecto

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase

data class Grupo(val id: String, val nombre: String, val descripcion: String) {}

class GrupoFunctions {
    private val database = FirebaseDatabase.getInstance().reference.child("Grupos")
    fun actualizarGrupo(grupoId: String, actualizaciones: Map<String, Any>): Task<Void> {
        val grupoRef = database.child(grupoId)
        return grupoRef.updateChildren(actualizaciones)
    }
}

