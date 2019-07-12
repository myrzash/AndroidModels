package kz.nice.modelsrxjava.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    private val BASE_URL = "https://restcountries.eu/rest/v2/";

    private var api: CountriesApi? = null

    constructor() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        api = retrofit.create(CountriesApi::class.java)
    }

    fun getCountries(): Single<List<Country>> = api!!.getCountries()
}