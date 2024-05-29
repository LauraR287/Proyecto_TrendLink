package com.lrincon.pruebas_proyecto

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MensajesFragment : Fragment(R.layout.bandeja_mensajes), GruposAdaptador.OnGrupoClickListener {

    private lateinit var database: DatabaseReference
    private lateinit var firebaseHelper: FirebaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var grupoAdapter: GruposAdaptador
    private lateinit var noGruposTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().reference.child("grupos")
        firebaseHelper = FirebaseHelper()
        recyclerView = view.findViewById(R.id.recyclerViewGrupos)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val buttonCrearGrupo = view.findViewById<Button>(R.id.crearGrupo)
        noGruposTextView = view.findViewById<Button>(R.id.noGruposTextView)

        firebaseHelper.consultarGrupo(database) { grupos ->
            if (grupos.isEmpty()) {
                noGruposTextView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                noGruposTextView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                grupoAdapter = GruposAdaptador(grupos, this)
                recyclerView.adapter = grupoAdapter
            }
        }

        buttonCrearGrupo.setOnClickListener {
            val fragment = CrearGrupoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onEditarGrupo(grupo: Grupo) {
        val fragment = ActualizarFragment()
        val bundle = Bundle()
        bundle.putString("idGrupo", grupo.idGrupo)
        bundle.putString("nombreGrupo", grupo.nombre)
        bundle.putString("descripcionGrupo", grupo.descripcion)
        fragment.arguments = bundle

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.containerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onEliminarGrupo(grupo: Grupo) {
        firebaseHelper.eliminarGrupo(database, grupo.idGrupo)
        firebaseHelper.consultarGrupo(database) { grupos ->
            grupoAdapter = GruposAdaptador(grupos, this)
            recyclerView.adapter = grupoAdapter
        }
    }

}
