package com.example.transporte

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    //Instancias
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //Definir barra del menu desplegable (overflow)
        val toolbar: Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)
        //Inicializar instancia
        beca = Beca()
        //Instancia para recibir informacion
        var infoRecibida = intent.extras
    if (infoRecibida != null){
        beca.folio = infoRecibida.getInt("folio")
        beca.institucion = infoRecibida.getString("institucion")!!
        beca.nombre = infoRecibida.getString("nombre")!!
        beca.apellido = infoRecibida.getString("apellido")!!
        beca.nivel = infoRecibida.getString("nivel")!!
        }//if
    }//onCreate

    //Mostrar u ocultar el menu en la Activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_desplegable, menu)
    //return super.onCreateOptionsMenu(menu)
        return true
    }//onCreateOptionsMenu

    //Ejecutar acción para opciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent?
        when(item.itemId){
            R.id.itFormulario -> {
                intent = Intent(applicationContext,
                    RegistroActivity::class.java)
                startActivity(intent)
            }
            R.id.itConsultar -> {
                intent = Intent(applicationContext,
                    ConsultaActivity::class.java)
                intent.putExtra("folio", beca.folio)
                intent.putExtra("institucion", beca.institucion)
                intent.putExtra("nombre", beca.nombre)
                intent.putExtra("apellido", beca.apellido)
                intent.putExtra("nivel", beca.nivel)
                startActivity(intent)
            }
            R.id.itCerrar -> { cerrarSesion() }
        }
        return super.onOptionsItemSelected(item)
    }//onOptionsItemSelected

    private fun cerrarSesion() {
        //Instancias donde se almacenará la información
        val preferences: SharedPreferences =
            getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        //Editor de preferencias, para agregar, asociando con preferencias
        val editor: SharedPreferences.Editor = preferences.edit()
        //Borrar información almacenada
        editor.clear()
        editor.apply()
        //Regresar a InicioActivity
        val intent = Intent(applicationContext, IngresoActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish() // Finaliza la actividad actual para que no quede en la pila de actividades
    }

}//class
