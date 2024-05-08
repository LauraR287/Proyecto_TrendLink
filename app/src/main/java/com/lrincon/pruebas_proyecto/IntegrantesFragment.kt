package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class IntegrantesFragment: Fragment(R.layout.fragment_seleccionado_integrantes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSeleccionar = view.findViewById<Button>(R.id.seleccionar)
        val buttonSiguiente = view.findViewById<Button>(R.id.siguiente)

        buttonSeleccionar.setOnClickListener {
            val fragment = CrearGrupoFragment()
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

        buttonSiguiente.setOnClickListener {
            val fragment = EditarFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}