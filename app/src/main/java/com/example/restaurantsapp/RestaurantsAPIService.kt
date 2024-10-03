package com.example.restaurantsapp
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantsAPIService {
    @GET("restaurants.json")
    suspend fun getRestaurant() : List<Restaurant>
}