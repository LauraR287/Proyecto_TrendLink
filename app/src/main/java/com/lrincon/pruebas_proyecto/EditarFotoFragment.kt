package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class EditarFotoFragment: Fragment(R.layout.fragment_foto_grupo) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSiguiente = view.findViewById<Button>(R.id.siguiente)

        buttonAtras.setOnClickListener {
            val fragment = EditarFragment()
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
