package com.needcode.rangkirku.network

import com.needcode.rangkirku.network.response.CityResponse
import retrofit2.Response
import retrofit2.http.GET

interface RajaOngkirEndPoint {

    @GET("city")
    suspend fun city(): Response<CityResponse>
}