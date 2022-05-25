package di.dev.pokedex.navegacion
/*
Sealed class is a class which restricts the class hierarchy. A class can be declared as sealed class using "sealed" keyword before the class name. It is used to represent restricted class hierarchy.
Sealed class is used when the object have one of the types from limited set, but cannot have any other type.
The constructors of sealed classes are private in default and cannot be allowed as non-private
*/

sealed class NavegacionPantallas(val pantalla: String){
    object PrimeraPantalla : NavegacionPantallas("primera_pantalla")
    object PokemonsPantalla : NavegacionPantallas("pokemons_pantalla")
}