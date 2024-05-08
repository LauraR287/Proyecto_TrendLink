package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.lrincon.pruebas_proyecto.Grupo


class EditarFragment: Fragment(R.layout.fragment_ajustes_grupo)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSiguiente = view.findViewById<Button>(R.id.siguiente)
        val buttonEditar = view.findViewById<ImageButton>(R.id.editar)

        val nombreGrupo = view.findViewById<EditText>(R.id.editTextUser)
        val DescripcionGrupo = view.findViewById<EditText>(R.id.descripcion)

        buttonAtras.setOnClickListener {
            val fragment = IntegrantesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonSiguiente.setOnClickListener {
            val GrupoFunctions = GrupoFunctions()
            val nombre = nombreGrupo.text.toString()
            val descripcion = DescripcionGrupo.text.toString()

            val nuevoGrupo = Grupo("", nombre, descripcion)

            GrupoFunctions.crearGrupo(nuevoGrupo)
                .addOnSuccessListener {
                    println("Grupo creado con éxito")
                }
                .addOnFailureListener { exception ->
                    println("Error al crear el grupo: ${exception.message}")
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