package com.example.restaurantsapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  RestaurantsViewModel(
    private val stateHandle: SavedStateHandle
) : ViewModel(){
    private var restInterface : RestaurantsAPIService
    val state = mutableStateOf(
        emptyList<Restaurant>()
    )
    private lateinit var restaurantCall : Call<List<Restaurant>>
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
        )
            .baseUrl("https://restaurantsapp-e2bc4-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .build()
        restInterface = retrofit.create(
                RestaurantsAPIService::class.java
            )
        getRestaurant()
    }

    fun toggleFavorite(id : Int) {
        val restaurant = state.value.toMutableList()
        val itemIndex = restaurant.indexOfFirst { it.id == id }
        val item = restaurant[itemIndex]
        restaurant[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurant[itemIndex])
        state.value = restaurant
    }

    private fun storeSelection(item: Restaurant) {
        val savedToggled = stateHandle.get<List<Int>?>(FAVORITES).orEmpty().toMutableList()
        if (item.isFavorite) savedToggled.add(item.id)
        else savedToggled.remove(item.id)
        stateHandle[FAVORITES] = savedToggled
    }
    companion object{
        const val FAVORITES = "favorites"
    }

    private fun List<Restaurant>.restoreSelections():
            List<Restaurant> {
        stateHandle.get<List<Int>?>(FAVORITES)?.let {
            selectedIds -> val restaurantsMap = this.associateBy { it.id }
            selectedIds.forEach { id -> restaurantsMap[id]?.isFavorite = true
            }
            return restaurantsMap.values.toList()
        }
        return this
    }

     fun getRestaurant() {
        restaurantCall = restInterface.getRestaurant()
        restaurantCall.enqueue(
            object : Callback<List<Restaurant>>{
                override fun onResponse(
                    call : Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    response.body()?.let{ restaurants ->
                        state.value =
                            restaurants.restoreSelections()
                    }
                }

                override fun onFailure(
                    call: Call<List<Restaurant>>,
                    t : Throwable
                ) {
                    t.printStackTrace()
                }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        restaurantCall.cancel()
    }
}



