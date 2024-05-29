// CONTROLLER
// Navegación del menú

package com.lrincon.pruebas_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        var inicioFragment = InicioFragment()
        var busquedaFragment = BusquedaFragment()
        var crearFragment = CrearFragment()
        var mensajesFragment = MensajesFragment()
        var perfilFragment = PerfilFragment()

        // Se le asigna un fragmento a cada icono de la barra de menú
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_inicio -> {
                    setCurrentFragment(inicioFragment)
                    true
                }
                R.id.nav_buscar -> {
                    setCurrentFragment(busquedaFragment)
                    true
                }
                R.id.nav_crear -> {
                    setCurrentFragment(crearFragment)
                    true
                }
                R.id.nav_mensajes -> {
                    setCurrentFragment(mensajesFragment)
                    true
                }
                R.id.nav_perfil -> {
                    setCurrentFragment(perfilFragment)
                    true
                }
                else -> false
            }
        }
    }

    // La función setCurrentFragment reemplaza el fragmento actual con un nuevo fragmento
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, fragment)
            commit()
        }
    }
}