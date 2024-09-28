package com.example.transporte

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroActivity : AppCompatActivity() {

//Instancias
private lateinit var folio: EditText
private lateinit var institucion: EditText
private lateinit var nombre: EditText
private lateinit var apellido: EditText
private lateinit var nivel: Spinner
private var beca: Beca = Beca()
private lateinit var nivelSel: String


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_registro)
    //Asociar con componentes gráficos
    folio = findViewById(R.id.edtFolio)
    institucion = findViewById(R.id.edtInstitucion)
    nombre = findViewById(R.id.edtNombre)
    apellido = findViewById(R.id.edtApellido)
    nivel = findViewById(R.id.spnNivel)
    //Definir valores de nivel
    val opciones = resources.getStringArray(R.array.niveles)
    //Vincular las opciones con el componente Spinner
    val adaptador = ArrayAdapter(this,
        android.R.layout.simple_spinner_dropdown_item, opciones)
    nivel.adapter = adaptador
    //Opción predeterminada
    nivelSel = opciones[0]
    //Escucha para determinar la opcion seleccionada del Spinner
    nivel.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2:
        Int, p3: Long) {
            nivelSel = opciones[p2]
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }
    }//AdapterView
}//onCreate

fun onClick(view: View?){
    when(view?.id){
        R.id.btRegistrar -> registrar()
        R.id.btLimpiar -> limpiar()
    }
}//onCLick

private fun registrar() {
//Validar que exista informacion en cajas de texto
    if(folio.text.isNotEmpty() && folio.text.isNotBlank() &&
        institucion.text.isNotBlank() &&
        institucion.text.isNotEmpty() && nombre.text.isNotEmpty() &&
        nombre.text.isNotBlank() &&
        apellido.text.isNotEmpty() && apellido.text.isNotBlank()){
        beca.folio = folio.text.toString().toInt()
        beca.institucion = institucion.text.toString()
        beca.nombre = nombre.text.toString()
        beca.apellido = apellido.text.toString()
        beca.nivel = nivelSel
        //Mensaje informativo
        Toast.makeText(this,"Información registrada.",
            Toast.LENGTH_LONG).show()
        //Regresar al menu principal
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("folio", beca.folio)
        intent.putExtra("institucion", beca.institucion)
        intent.putExtra("nombre", beca.nombre)
        intent.putExtra("apellido", beca.apellido)
        intent.putExtra("nivel", beca.nivel)
        startActivity(intent)
    } else {
        Toast.makeText(this,"Capturar información",
            Toast.LENGTH_LONG).show()
    }
}//registrar
    private fun limpiar() {
        folio.text = null
        institucion.text = null
        nombre.text = null
        apellido.text = null
        folio.requestFocus()
    }//limpiar

}//class