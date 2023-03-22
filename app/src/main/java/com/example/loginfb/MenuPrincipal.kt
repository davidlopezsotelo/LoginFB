package com.example.loginfb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


@Suppress("DEPRECATION")
class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        mensaje()
        setup()


    }

     private fun mensaje() {
        val builder = AlertDialog.Builder(this)// creamos un cuadro de dialogo

        builder.setTitle("Hola, Usuario")
        builder.setMessage("Te has identificado correctamente.")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun setup(){

        val BotonSalir=findViewById<Button>(R.id.button_salir)

        title="Menu Principal"


        BotonSalir.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}