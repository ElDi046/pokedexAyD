package di.dev.pokedex.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import di.dev.pokedex.data.Result
import di.dev.pokedex.mvvm.PokemonViewModel
import di.dev.pokedex.navegacion.NavegacionPantallas

@Composable
fun PokemonsPantalla(navController: NavController){
    val pokemonViewModel: PokemonViewModel = PokemonViewModel()

    //Scaffold agrega botones de back, un espacio y el texto "Pokedex List"
    Scaffold(topBar = {
        TopAppBar {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                modifier = Modifier.clickable {
                    //popBackStack revierte la última transacción añadida al BackStack
                    navController.popBackStack()
                })
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Pokedex List")
        }
    }) {
        Pokemones(pokemons = pokemonViewModel.listaPokemons)
        pokemonViewModel.getPokemons()
    }
}

@Composable
fun Pokemon(result: Result){
    val expanded = remember { mutableStateOf(false)}
    val extraPadding = if(expanded.value) 48.dp else 0.dp
    // Surface es el espacio o superficie en la que se van a organizar nuestros elementos gráficos. Parecido a un Layout
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)){
        //Row sustituye al linearLayout, organiza elementos de manera consecutiva en un renglón.
        Row(modifier = Modifier.padding(25.dp)){
            Column(
                Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ){
                Text("Pokemon: ")
                Text(text = result.name)
            }
            //OutLinedButton crea un botón con un borde suave.
            OutlinedButton(onClick = { expanded.value = !expanded.value}) {
                Text(if (expanded.value) "hide" else "catch")
            }
        }
    }
}

@Composable
private fun Pokemones(pokemons: List<Result>){
    val scrollState = rememberScrollState()

    LazyColumn{
        itemsIndexed(items = pokemons){index, item ->
            Pokemon(result = item)
        }
    }
}