package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class EditarFragment: Fragment(R.layout.fragment_ajustes_grupo)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSiguiente = view.findViewById<Button>(R.id.siguiente)
        val buttonEditar = view.findViewById<ImageButton>(R.id.editar)

        buttonAtras.setOnClickListener {
            val fragment = IntegrantesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonSiguiente.setOnClickListener {
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