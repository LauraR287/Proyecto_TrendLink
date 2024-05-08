package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class CrearGrupoFragment: Fragment(R.layout.frament_seleccionar_integrantes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSeleccionar = view.findViewById<Button>(R.id.seleccionar)

        buttonSeleccionar.setOnClickListener {
            val fragment = IntegrantesFragment()
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