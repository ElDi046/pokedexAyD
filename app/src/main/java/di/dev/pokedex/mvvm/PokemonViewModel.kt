package di.dev.pokedex.mvvm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import di.dev.pokedex.data.Result
import di.dev.pokedex.pantallas.Pokemon
import di.dev.pokedex.service.PokemonApi
import kotlinx.coroutines.launch

/*
Para cada ViewModel que creemos tenemos que crear su propio viewModelScope(),
dentro de este irá la corrutina que será cancelada cuando el ViewModel se limpie para no consumir recursos.
*/

class PokemonViewModel : ViewModel() {
    var listaPokemons : List<Result> by mutableStateOf(listOf())
    var errorMessage : String by mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var curPage = 0

    fun getPokemons(){
        viewModelScope.launch {
            val apiService = PokemonApi.getInstance()
            try {
                val pokemons = apiService.getPokemonList()
                listaPokemons = pokemons.results
                Log.i("Pokedex", listaPokemons.toString())
            } catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}