package com.example.pet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pet.ui.theme.PetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetTheme {
                PetSelectionScreen()
            }
        }
    }
}

@Composable
fun PetSelectionScreen() {
    // Mapa con categorías y sus razas
    val petCategories = mapOf(
        "Pastores" to listOf("Pastor Catalán", "Pastor Alemán", "Komodor", "Pastor Australiano"),
        "Pinscher" to listOf("Dobermann", "Pinscher Alemán", "Pinscher Miniatura", "Schnauzer"),
        "Terrier" to listOf("Fox Terrier", "Bull Terrier", "Jack Russell Terrier"),
        "Retriever" to listOf("Labrador Retriever", "Golden Retriever", "Flat-Coated Retriever")
    )

    var selectedCategory by remember { mutableStateOf(petCategories.keys.first()) }
    var selectedBreeds by remember { mutableStateOf(petCategories[selectedCategory] ?: emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Selecciona una categoría de mascota:", style = MaterialTheme.typography.titleMedium)

        // Spinner (Dropdown) para seleccionar la categoría
        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.padding(vertical = 8.dp)) {
            Button(onClick = { expanded = true }) {
                Text(selectedCategory)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                petCategories.keys.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            selectedBreeds = petCategories[category] ?: emptyList()
                            expanded = false
                        }
                    )
                }
            }
        }

        // Lista de razas según la categoría elegida
        Text(text = "Razas disponibles:", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
        selectedBreeds.forEach { breed ->
            ClickableText(
                text = AnnotatedString(breed),
                onClick = { /* Acción si se desea hacer algo con la raza */ },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PetSelectionPreview() {
    PetTheme {
        PetSelectionScreen()
    }
}
