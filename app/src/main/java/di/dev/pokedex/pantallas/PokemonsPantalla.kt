package di.dev.pokedex.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import di.dev.pokedex.data.Result
import di.dev.pokedex.mvvm.PokemonViewModel

@Composable
fun PokemonsPantalla(navController: NavController){
    val pokemonViewModel: PokemonViewModel = PokemonViewModel()
    //Scaffold agrega botones de back, un espacio y el texto "Pokedex List"
    Scaffold(topBar = {
        Modifier.height(100.dp)
        TopAppBar{
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                modifier = Modifier.clickable {
                    //popBackStack revierte la última transacción añadida al BackStack
                    navController.popBackStack()
                })
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Pokedex List")
            SearchBar()
        }
    })
    {
        Pokemones(pokemons = pokemonViewModel.listaPokemons)
        pokemonViewModel.getPokemons()
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    var text by remember{
        mutableStateOf("")
    }
    Box(modifier = modifier) {
        TextField(

            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(26.dp))
                .clip(AbsoluteRoundedCornerShape(20.dp)),
            value = text,
            placeholder = {
                    Text(text = "Looking for a pokemon?")
            },
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Color.Black,
            )
        )
    }
}

@Composable
fun Pokemon(result: Result){
    val expanded = remember { mutableStateOf(false)}
    val extraPadding = if(expanded.value) 48.dp else 0.dp
    // Surface es el espacio o superficie en la que se van a organizar nuestros elementos gráficos. Parecido a un Layout
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 12.dp),
        ){
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
        itemsIndexed( items = pokemons){index, item ->
            Pokemon(result = item)
        }
    }
}