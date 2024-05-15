package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class ActualizarFragment: Fragment(R.layout.fragment_editar_grupo)  {
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
            val GrupoFunctions = GrupoFunctions()
            val grupoId = "grupoId"
            val actualizaciones = mapOf(
                "nombre" to nombreGrupo.text.toString(),
                "descripcion" to DescripcionGrupo.text.toString()
            )

            GrupoFunctions.actualizarGrupo(grupoId, actualizaciones)
                .addOnSuccessListener {
                    println("Datos actualizados con éxito")  // Confirmar éxito
                }
                .addOnFailureListener { exception ->
                    println("Error al actualizar datos: ${exception.message}")  // Manejar errores
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