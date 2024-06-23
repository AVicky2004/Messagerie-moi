package com.example.ictmessenger

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {
    private val dbUrl = "jdbc:mysql://192.168.1.100:3306/root2"
    private val username = "root2"
    private val password = "Ange2004**"

    /**
     * Établit une connexion à la base de données MySQL.
     * @return Un objet Connection si la connexion est réussie, sinon null.
     */
    fun connect(): Connection? {
        try {
            val connection = DriverManager.getConnection(dbUrl, username, password)
            println("Connexion établie avec succès!")
            return connection
        } catch (e: SQLException) {
            println("Erreur lors de l'établissement de la connexion : ${e.message}")
            return null
        }
    }

    fun main() {
        // Instanciez la classe DatabaseConnection
        val dbConnection = DatabaseConnection()

        // Établissez une connexion à la base de données
        val connection = dbConnection.connect()

        // Utilisez la connexion ici
        // N'oubliez pas de fermer la connexion après utilisation
        connection?.close()
    }
}