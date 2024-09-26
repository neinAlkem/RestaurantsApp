package com.example.restaurantsapp
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantsAPIService {
    @GET("restaurants.json")
    fun getRestaurant() : Call<List<Restaurant>>
}