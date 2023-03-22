package com.example.loginfb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


/* CREACION DE PROYECTO PARA LOGEARSE CON FIREBASE

1º creamos proyecto en firebase con el mismo nombre
2º Agregamos Firebase al proyecto utilizando la consola de Firebase (Tools/Firebase)
     (añadimos primero Analitics y declaramos e inicializamos este.
     despues authentication(custom) y hacemos lo mismo)
3º habilitamos correo electronico en la consola de Firebase
4º creamos el bundle (https://www.youtube.com/watch?v=sCPpzqWsDvI)
5º creamos la funcion setup(), para autentication
6º cambiamos el nombre de la activity por LoginActivity
7º Creamos la Activity , MenuPrincipal, la que dara acceso a nuestra app
8º creamos proyecto en github: VSC


---------mejoramos el login-------------



 */

//creamos variable analytics
private lateinit var analytics: FirebaseAnalytics

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //inicializamos variable
        analytics = Firebase.analytics

        val analityc = FirebaseAnalytics.getInstance(this)

        //creamos un Bundle para pasar objetos entre activitys
        val bundle = Bundle()
        bundle.putString("mensaje", "Integracion de Firebase completa")
        analityc.logEvent("InitScreen", bundle)

        //setup
        setup()

    }

    private fun setup() {

        title = "Autenticacion"

        val botonIniciar = findViewById<Button>(R.id.button_Iniciar)
        val botonregistrar=findViewById<Button>(R.id.button_Registrar)
        val textEmail = findViewById<EditText>(R.id.editTextEmail)
        val textContraseña = findViewById<EditText>(R.id.editTextContraseña)


        //Funciones de boton REGISTRARSE
        botonregistrar.setOnClickListener {
            if (textEmail.text.isNotEmpty() && textContraseña.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        textEmail.text.toString(),
                        textContraseña.text.toString()
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showRegistro()
                        } else {
                            showAlert()// mensaje de alerta
                        }
                    }
            }else{
                showRellenar()
            }

        }//--------------------------------------------------------

        //funciones de boton INICIAR
        botonIniciar.setOnClickListener{
            if (textEmail.text.isNotEmpty() && textContraseña.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(// cambiar la funcion para sing, el resto es igual que el otro boton
                        textEmail.text.toString(),
                        textContraseña.text.toString()
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome() //lo qeu quieres que haga...
                        } else {
                            showAlert()// mensaje de alerta
                        }
                    }
            }else{
                showRellenar()
            }
        }



    }

    private fun showRellenar() {

        //creamos una funcion que mostrara un  mensaje de alerta mediante un cuadro de dialogo
        val builder= AlertDialog.Builder(this)// creamos un cuadro de dialogo

        builder.setTitle("ERROR DE AUTENTICACION!!")
        builder.setMessage("Deves de rellenar todos los campòs correctamente.")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()

    }

    private fun showRegistro() {
        //creamos una funcion que mostrara un  mensaje de alerta mediante un cuadro de dialogo
        val builder= AlertDialog.Builder(this)// creamos un cuadro de dialogo

        builder.setTitle("Registro")
        builder.setMessage("Te has registrado correctamente.")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    //creamos una funcion que mostrara un  mensaje de alerta mediante un cuadro de dialogo
    private fun showAlert(){

        val builder= AlertDialog.Builder(this)// creamos un cuadro de dialogo

        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario.")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }//ff

    //creamos una funcion que hara[...]cuando ingresemos en ka aplicacion

    private fun showHome(){

        val homeIntent = Intent(this,MenuPrincipal::class.java).apply {

            //creamos un cuadro de dialogo que nos diga que estamos dentro de la app



        }
        startActivity(homeIntent)
    }
}