package com.lrincon.pruebas_proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

// Permite mostrar una lista de grupos en un RecyclerView. 
// Cada ítem de la lista tiene un botón de menú que permite al usuario realizar acciones como editar, ver información o eliminar un grupo.
class GruposAdaptador(
    private val grupos: List<Grupo>,
    private val listener: OnGrupoClickListener
) : RecyclerView.Adapter<GruposAdaptador.GrupoViewHolder>() {

    interface OnGrupoClickListener {
        fun onEditarGrupo(grupo: Grupo)
        fun onInfoGrupo(grupo: Grupo)
        fun onEliminarGrupo(grupo: Grupo)
    }

    class GrupoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreGrupoTextView: TextView = itemView.findViewById(R.id.nombreGrupoTextView)
        val menuButton: ImageButton = itemView.findViewById(R.id.menuButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrupoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.grupo_item, parent, false)
        return GrupoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GrupoViewHolder, position: Int) {
        val grupo = grupos[position]
        holder.nombreGrupoTextView.text = grupo.nombre

        holder.menuButton.setOnClickListener {
            val popup = PopupMenu(holder.itemView.context, holder.menuButton)
            popup.inflate(R.menu.menu_grupo)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_editar_grupo -> {
                        listener.onEditarGrupo(grupo)
                        true
                    }
                    R.id.action_info_grupo -> {
                        listener.onInfoGrupo(grupo)
                        true
                    }
                    R.id.action_eliminar_grupo -> {
                        listener.onEliminarGrupo(grupo)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
    override fun getItemCount() = grupos.size
}


