package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class MensajesFragment: Fragment(R.layout.fragment_bandeja_mensajes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCrearGrupo = view.findViewById<Button>(R.id.crearGrupo)
        buttonCrearGrupo.setOnClickListener {
            val fragment = CrearGrupoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}