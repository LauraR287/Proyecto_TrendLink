package com.lrincon.pruebas_proyecto

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase

class ActualizarFragment: Fragment(R.layout.editar_grupo)  {

    private val database = FirebaseDatabase.getInstance()
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSiguiente = view.findViewById<Button>(R.id.siguiente)
        val buttonEditar = view.findViewById<ImageButton>(R.id.editar)

        val nombreGrupo = view.findViewById<EditText>(R.id.editTextUser)
        val DescripcionGrupo = view.findViewById<EditText>(R.id.descripcion)

        buttonAtras.setOnClickListener {
            val fragment = GrupoCreadoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonSiguiente.setOnClickListener {

            val grupoId = "NwM48IkJ3nxeap5H1Si" // Cambia esto al ID del grupo que deseas mostrar

            database.getReference("grupos").child(grupoId).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val grupo = dataSnapshot.getValue(Grupo::class.java)
                    editTextNombre.setText(grupo?.nombre)
                    editTextDescripcion.setText(grupo?.descripcion)

                } else {
                    Log.d(TAG, "No se encontró el documento")
                }
            }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error al obtener el documento:", exception)
                }

            val fragment = GrupoCreadoFragment()
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
