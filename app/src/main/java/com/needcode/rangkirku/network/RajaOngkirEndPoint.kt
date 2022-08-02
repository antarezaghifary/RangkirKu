package com.needcode.rangkirku.network

import com.needcode.rangkirku.network.response.CityResponse
import com.needcode.rangkirku.network.response.CostResponse
import com.needcode.rangkirku.network.response.SubdistrictResponse
import com.needcode.rangkirku.network.response.WaybillResponse
import retrofit2.Response
import retrofit2.http.*

interface RajaOngkirEndPoint {

    @GET("city")
    suspend fun city(): Response<CityResponse>

    @GET("subdistrict")
    suspend fun subdistrict(
        @Query("city") city: String
    ): Response<SubdistrictResponse>

    @FormUrlEncoded
    @POST("cost")
    suspend fun cost(
        @Field("origin") origin: String,
        @Field("originType") originType: String,
        @Field("destination") destination: String,
        @Field("destinationType") destinationType: String,
        @Field("weight") weight: String,
        @Field("courier") courier: String
    ): Response<CostResponse>

    @FormUrlEncoded
    @POST("waybill")
    suspend fun waybill(
        @Field("waybill") waybill: String,
        @Field("courier") courier: String
    ): Response<WaybillResponse>
}