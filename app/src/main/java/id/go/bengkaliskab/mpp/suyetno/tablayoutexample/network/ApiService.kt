package id.go.bengkaliskab.mpp.suyetno.tablayoutexample.network

import id.go.bengkaliskab.mpp.suyetno.tablayoutexample.model.PersonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("persons")
    fun getPersons(
        @Query("_quantity") quantity: Int,
        @Query("_locale") locale: String,
        @Query("_gender") gender: String,
        @Query("_birthday_start") birthday: String = "1990-01-01" // Parameter tambahan agar data lebih unik
    ): Call<PersonResponse>
}