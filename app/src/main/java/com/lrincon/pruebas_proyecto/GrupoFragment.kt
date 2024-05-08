package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class GrupoCreadoFragment: Fragment(R.layout.fragment_grupo_creado) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCrearGrupo = view.findViewById<Button>(R.id.crearGrupo)
        val buttonEliminar = view.findViewById<ImageButton>(R.id.eliminar)

        buttonCrearGrupo.setOnClickListener {
            val fragment = CrearGrupoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonEliminar.setOnClickListener {
            val fragment = EliminarFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}