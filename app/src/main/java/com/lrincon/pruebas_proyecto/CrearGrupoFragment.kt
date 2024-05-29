package com.lrincon.pruebas_proyecto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Se crea un nuevo grupo, se requiere de nombre del grupo, descripción y los integrantes.
class CrearGrupoFragment: Fragment(R.layout.ajustes_grupo) {

    private lateinit var database: DatabaseReference
    private lateinit var databaseUsuarios: DatabaseReference
    private lateinit var firebaseHelper: FirebaseHelper

    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText

    private lateinit var recyclerView: RecyclerView
    private lateinit var usuarioAdapter: UsuariosAdaptador

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().reference.child("grupos")
        databaseUsuarios = FirebaseDatabase.getInstance().reference.child("usuarios")
        firebaseHelper = FirebaseHelper()

        recyclerView = view.findViewById(R.id.usuariosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonSiguiente = view.findViewById<Button>(R.id.siguiente)

        editTextNombre = view.findViewById(R.id.nombreGrupo)
        editTextDescripcion = view.findViewById(R.id.descripcionGrupo)

        // Se muestran todos los chats grupales que se tienen activos
        firebaseHelper.consultarUsuarios(databaseUsuarios) { usuarios ->
            usuarioAdapter = UsuariosAdaptador(usuarios, this)
            recyclerView.adapter = usuarioAdapter
        }

        buttonSiguiente.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            val selectedUsuarios = usuarioAdapter.getSelectedUsuarios()
            val integrantes = selectedUsuarios.joinToString(",") { it.nombre }

            // Crear el grupo con los usuarios seleccionados
            firebaseHelper.crearGrupo(database, nombre, descripcion, integrantes)

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
