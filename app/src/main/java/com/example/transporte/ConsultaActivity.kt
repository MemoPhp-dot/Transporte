package com.example.transporte

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConsultaActivity : AppCompatActivity() {

    //Instancias
    private lateinit var info: TextView
    private var beca: Beca = Beca()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)
        //Asociar con componente gráficos
        info = findViewById(R.id.txtInfo)

        //Instancia para recibir información
        val infoRecibida = intent.extras
        beca.folio = infoRecibida?.getInt("folio")!!
        beca.institucion = infoRecibida?.getString("institucion")!!
        beca.nombre = infoRecibida?.getString("nombre")!!
        beca.apellido = infoRecibida?.getString("apellido")!!
        beca.nivel = infoRecibida?.getString("nivel")!!
        //Colocar la información dentro del TextView
        info.text = "Información registrada:\n" +
                "Folio: ${beca.folio}\n" +
                "Institución: ${beca.institucion}\n" +
                "Nombre: ${beca.nombre}\n" +
                "Apellidos: ${beca.apellido}\n"
    }
}