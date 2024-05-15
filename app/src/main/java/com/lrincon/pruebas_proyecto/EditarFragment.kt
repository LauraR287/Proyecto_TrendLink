package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class EditarFragment: Fragment(R.layout.ajustes_grupo)  {

    private val database = FirebaseDatabase.getInstance().reference.child("grupos")
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSiguiente = view.findViewById<Button>(R.id.siguiente)
        val buttonEditar = view.findViewById<ImageButton>(R.id.editar)

        editTextNombre = view.findViewById(R.id.nombreGrupo)
        editTextDescripcion = view.findViewById(R.id.descripcionGrupo)

        buttonSiguiente.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            val grupoId = database.push().key
            if (grupoId != null) {
                val grupo = Grupo(descripcion, grupoId, nombre)
                database.child(grupoId).setValue(grupo)

                val fragment = GrupoCreadoFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.containerView, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
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
