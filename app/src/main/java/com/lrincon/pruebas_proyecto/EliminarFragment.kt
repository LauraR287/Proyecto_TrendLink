package com.lrincon.pruebas_proyecto

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase

class EliminarFragment: Fragment(R.layout.verificacion_eliminar) {

    private val database = FirebaseDatabase.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEliminarGrupo = view.findViewById<Button>(R.id.eliminarGrupo)
        val buttonAtras = view.findViewById<Button>(R.id.atras)

        buttonEliminarGrupo.setOnClickListener {
            val grupoId = "NwM48IkJ3nxeap5H1Si" // Cambia esto al ID del grupo que deseas eliminar

            database.getReference("grupos").child(grupoId).removeValue()

            val fragment = MensajesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonAtras.setOnClickListener {
            val fragment = GrupoCreadoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
