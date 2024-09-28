package com.example.transporte

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    //Instancia Intent para lanzar una Activity
    private lateinit var intent: Intent
    private lateinit var beca: Beca


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beca = Beca()

        // Instancia para generar un hilo para lanzar Activity de Ingreso o Menú
        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (nuevoUsuario()) {
                    intent = Intent(applicationContext, MenuActivity::class.java)
                    // Añadir extras al intent
                    intent.putExtra("folio", beca.folio)
                    intent.putExtra("institucion", beca.institucion)
                    intent.putExtra("nombre", beca.nombre)
                    intent.putExtra("apellido", beca.apellido)
                    intent.putExtra("nivel", beca.nivel)
                } else {
                    intent = Intent(applicationContext, IngresoActivity::class.java)
                }
                startActivity(intent)
                finish()  // Finaliza la actividad actual para que no pueda volverse atrás con el botón de retroceso
            }
        }, 2000)
    }

    private fun nuevoUsuario(): Boolean {
        // Acceso a las preferencias
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        // Revisar si el usuario fue guardado previamente
        val guardado = preferences.getBoolean("guardado", false)

        // Si está guardado, también podrías recuperar el correo, por ejemplo
        val correoGuardado = preferences.getString("correo", null)

        return guardado && correoGuardado != null
    }
}