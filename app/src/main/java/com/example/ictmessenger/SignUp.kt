package com.example.ictmessenger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class SignUp : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSignUp: Button

    fun enregistrerDonnees(username: String, email: String, password: String) {
        val dbConnection = DatabaseConnection()
        val connection: Connection? = dbConnection.connect()

        // Vérifiez si la connexion est réussie
        if (connection != null) {
            val insertQuery = "INSERT INTO user (email, password, username) VALUES (?, ?, ?)"
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement(insertQuery)
                // Assignez les valeurs aux paramètres de la requête
                preparedStatement.setString(1, email)
                preparedStatement.setString(2, password)
                preparedStatement.setString(3, username)

                // Exécutez la requête d'insertion
                val rowsAffected = preparedStatement.executeUpdate()
                println("Enregistrement réussi, $rowsAffected ligne(s) insérée(s).")

                // Fermer le statement après utilisation
                preparedStatement.close()
            } catch (e: SQLException) {
                println("Erreur lors de l'exécution de la requête : ${e.message}")
            } finally {
                // Fermer la connexion après utilisation
                connection.close()
            }
        } else {
            println("Impossible d'établir une connexion à la base de données.")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)



        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        edtName = findViewById(R.id.edt_name)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener{
            enregistrerDonnees(edtEmail.toString(), edtPassword.toString(), edtName.toString());
           /* val intent = Intent(this, SignUp::class.java)
            startActivity(intent)*/

        }

    }
}