package di.dev.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import di.dev.pokedex.ui.theme.PokedexTheme

/*
¿definir un renglón en xml? -> Es correcto, debemos de definir que se va a utilizar un ListView
¿tener una clase Adapter y una clase ViewHolder? -> Es correcto, necesitamos el adapter para poder mostrar la información dentro de nuestra aplicación
¿Se requiere de una lista de datos? -> Es correcto, necesitamos la lista donde se obtendrá la información para mostrarla.
¿en qué hilo se crea la lista? -> Se crea un hilo diferente al Main Thread para que pueda hacer funciones asincronas dentro de la aplicación.
Algunas de estas funciones puede ser obtener la información de la API.

 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        Greeting("Android")
    }
}