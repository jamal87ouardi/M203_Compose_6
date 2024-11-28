package com.example.m203_compose_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.m203_compose_6.ui.theme.M203_Compose_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            M203_Compose_6Theme {
                CinemaApp()
            }
        }
    }
}

@Composable
fun CinemaApp() {
    // États pour les TextField, RadioButton, et Switch
    var name by remember { mutableStateOf("") }
    var ticketCount by remember { mutableStateOf("") }
    var seatType by remember { mutableStateOf("") }
    var isLoyalMember by remember { mutableStateOf(false) }
    var totalPrice by remember { mutableStateOf(0) }

    // Interface utilisateur
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("Billets de Cinéma")
        // Champ de texte pour le nom
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom du client") },
            modifier = Modifier.fillMaxWidth()
        )

        // Champ de texte pour le nombre de billets
        TextField(
            value = ticketCount,
            onValueChange = { ticketCount = it },
            label = { Text("Nombre de billets") },
            modifier = Modifier.fillMaxWidth(),
            )


        // RadioButtons pour le type de place
        Text("Type de place :")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = seatType == "Standard",
                    onClick = { seatType = "Standard" }
                )
                Text("Standard\n(50 dh)")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = seatType == "Premium",
                    onClick = { seatType = "Premium" }
                )
                Text("Premium\n(100 dh)")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = seatType == "VIP",
                    onClick = { seatType = "VIP" }
                )
                Text("VIP\n(150 dh)")
            }
        }

        // Switch pour Membre Fidèle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Membre fidèle :")
            Switch(
                checked = isLoyalMember,
                onCheckedChange = { isLoyalMember = it }
            )
        }

        // Bouton pour calculer le prix total
        Button(
            onClick = {
                val ticketCountInt = ticketCount.toIntOrNull() ?: 0
                var price = when (seatType) {
                    "Standard" -> ticketCountInt * 50
                    "Premium" -> ticketCountInt * 100
                    "VIP" -> ticketCountInt * 150
                    else -> 0
                }
                if (isLoyalMember) {
                    price = (price * 0.85).toInt() // 15 % de réduction
                }
                totalPrice = price
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculer")
        }

        // Résultats
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nom du client : $name")
        Text("Prix total : $totalPrice dh")
    }
}
