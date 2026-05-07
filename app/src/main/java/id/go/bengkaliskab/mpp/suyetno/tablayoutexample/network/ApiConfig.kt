package id.go.bengkaliskab.mpp.suyetno.tablayoutexample.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiConfig {
    private const val BASE_URL = "https://fakerapi.it/api/v2/"

    fun getApiService(): ApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}