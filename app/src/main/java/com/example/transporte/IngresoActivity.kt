package com.example.transporte

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IngresoActivity : AppCompatActivity() {
    //Instancias
    private lateinit var correo: EditText
    private lateinit var contrasena: EditText
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var guardar: Switch
    private lateinit var beca: Beca

    // Arreglo de usuarios predefinidos
    private val usuarios = arrayOf(
        Usuario("ggarcia@gmail.com", "gmail", true),
        Usuario("ggarcia@outlook.com", "outlook", true),
        Usuario("ggarcia@hotmail.com", "hotmail", true),
        Usuario("ggaria@hubspot.com", "hubspot", true),
        Usuario("ggarcia@guiseo.com", "guiseo", true)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)
        //Asociar con componentes gráficos
        correo = findViewById(R.id.edtCorreo)
        contrasena = findViewById(R.id.edtContrasena)
        guardar = findViewById(R.id.swtGuardar)
        //Inicializar objeto
        beca = Beca()
    }//onCreate

    fun onClick(view: View?){
        when(view?.id){
            R.id.btIngresar -> ingresar()
            R.id.btnBorrar -> limpiar()
        }
    }//onCLick

    private fun ingresar() {
        // Validar que existan datos
        if (correo.text.isNotBlank() && contrasena.text.isNotBlank()) {
            // Verificar si el usuario existe en el arreglo de usuarios predefinidos
            val usr = usuarios.find {
                it.correo == correo.text.toString() && it.contrasena == contrasena.text.toString()
            }

            if (usr != null) {
                // Si las credenciales coinciden, continúa a la siguiente pantalla
                if (guardar.isChecked) {
                    guardarPreferencias(usr)
                }

                val intent = Intent(applicationContext, MenuActivity::class.java)
                intent.putExtra("folio", beca.folio)
                intent.putExtra("institucion", beca.institucion)
                intent.putExtra("nombre", beca.nombre)
                intent.putExtra("apellido", beca.apellido)
                intent.putExtra("nivel", beca.nivel)
                startActivity(intent)
            } else {
                // Si las credenciales no coinciden
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        } else {
            // Si los campos están vacíos
            Toast.makeText(this, "Capturar información", Toast.LENGTH_LONG).show()
        }
    }

    private fun limpiar() {
        correo.text = null
        contrasena.text = null
        correo.requestFocus()
    }//limpiar()

    private fun guardarPreferencias(usr: Usuario) {
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("guardado", true)
        editor.putString("correo", usr.correo) // Guardar el correo
        editor.apply()

    }
}//class
