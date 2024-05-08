package com.lrincon.pruebas_proyecto

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase

class CambiarDatosFragment : Fragment(R.layout.fragment_editar_grupo) {

    private val database = FirebaseDatabase.getInstance().reference.child("Grupos")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextNombreGrupo = view.findViewById<EditText>(R.id.editTextUser)
        val editTextDescripcionGrupo = view.findViewById<EditText>(R.id.descripcion)

        val buttonCrearGrupo = view.findViewById<Button>(R.id.siguiente)
        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonEditar = view.findViewById<ImageButton>(R.id.editar)


        buttonCrearGrupo.setOnClickListener {
            val fragment = GrupoCreadoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonAtras.setOnClickListener {
            val fragment = IntegrantesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonEditar.setOnClickListener {
            val fragment = EditarFotoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}

