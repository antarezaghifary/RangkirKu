package com.needcode.rangkirku.network

import com.needcode.rangkirku.database.preferences.*

class RangkirRepository(
    private val api: RajaOngkirEndPoint,
    private val pref: RangkirPreferences
) {

    //fetchCity(): Response<CityResponse>{return api.city()}
    suspend fun city() = api.city()
    suspend fun subdistrict(cityId: String) = api.subdistrict(cityId)

    fun savePreferences(type: String, id: String, name: String) {
        when (type) {
            "origin" -> {
                pref.put(prefOriginId, id)
                pref.put(prefOriginName, name)
            }

            "destination" -> {
                pref.put(prefDestibationId, id)
                pref.put(prefDestinationName, name)
            }
        }
    }

    fun getPreferences(): List<PreferencesModel> {
        return listOf(
            PreferencesModel(
                type = "origin", id = pref.getString(prefOriginId), name = pref.getString(
                    prefOriginName
                )
            ),
            PreferencesModel(
                type = "destination", id = pref.getString(prefDestibationId), name = pref.getString(
                    prefDestinationName
                )
            )
        )
    }

    suspend fun cost(
        origin: String,
        originType: String,
        destination: String,
        destinationType: String,
        weight: String,
        courier: String
    ) = api.cost(
        origin,
        originType,
        destination,
        destinationType,
        weight,
        courier
    )

    suspend fun waybill(
        waybill: String,
        courier: String
    ) = api.waybill(
        waybill,
        courier
    )
}