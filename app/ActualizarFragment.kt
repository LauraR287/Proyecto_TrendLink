package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActualizarFragment : Fragment(R.layout.editar_grupo) {

    private lateinit var database: DatabaseReference
    private lateinit var firebaseHelper: FirebaseHelper

    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().reference.child("grupos")
        firebaseHelper = FirebaseHelper()

        editTextNombre = view.findViewById(R.id.nombreGrupo)
        editTextDescripcion = view.findViewById(R.id.descripcionGrupo)

        val buttonCrearGrupo = view.findViewById<Button>(R.id.siguiente)
        val buttonAtras = view.findViewById<Button>(R.id.atras)

        buttonCrearGrupo.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            var grupoId = arguments?.getString("idGrupo")

            if (grupoId != null) {
                firebaseHelper.editarGrupo(database, grupoId, nombre, descripcion)
            }

            val fragment = MensajesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonAtras.setOnClickListener {
            val fragment = MensajesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}