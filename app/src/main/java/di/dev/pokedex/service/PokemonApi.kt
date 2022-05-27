package di.dev.pokedex.service

import di.dev.pokedex.data.ListaPokemons
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//La función termina el llamado en @GET("pokemon") porque hace un request a la API de pokemon

interface PokemonApi {
    @GET("pokemon")
    suspend fun  getPokemonList(): ListaPokemons

    companion object{
        var apiService: PokemonApi? = null
        fun getInstance() : PokemonApi{
            if(apiService == null){
                apiService = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build().create(PokemonApi::class.java)
            }
            return apiService!!
        }
    }
}

