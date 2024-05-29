package com.lrincon.pruebas_proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsuariosAdaptador(
    private val usuarios: List<Usuario>,
    private val fragment: CrearGrupoFragment
) : RecyclerView.Adapter<UsuariosAdaptador.UsuarioViewHolder>() {

    private val selectedUsuarios = mutableSetOf<Usuario>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.usuario_item, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.userName.text = usuario.nombre
        holder.userCheckbox.setOnCheckedChangeListener(null)
        holder.userCheckbox.isChecked = selectedUsuarios.contains(usuario)

        holder.userCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedUsuarios.add(usuario)
            } else {
                selectedUsuarios.remove(usuario)
            }
        }
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    fun getSelectedUsuarios(): List<Usuario> {
        return selectedUsuarios.toList()
    }

    class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName)
        val userCheckbox: CheckBox = view.findViewById(R.id.userCheckbox)
    }
}

