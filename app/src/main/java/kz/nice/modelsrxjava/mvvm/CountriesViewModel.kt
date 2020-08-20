package kz.nice.modelsrxjava.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.nice.modelsrxjava.model.CountriesService
import java.util.concurrent.TimeUnit

class CountriesViewModel : ViewModel {
    private var service: CountriesService
    private var countries: MutableLiveData<List<String>> = MutableLiveData()
    private var countryError: MutableLiveData<Boolean> = MutableLiveData()

    constructor() {
        service = CountriesService()
        fetchCountries()
    }

    private fun fetchCountries() {
        service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .timeout(3, TimeUnit.SECONDS)
            .map {
                it.map { country ->
                    country.name.toString()
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                countries.value = it
                countryError.value = false
            }, {
                countryError.value = true
            })
    }

    fun getCountries(): LiveData<List<String>> {
        return this.countries
    }

    fun getCountryError(): LiveData<Boolean> {
        return this.countryError
    }

    fun onRefresh() {
        this.fetchCountries()
    }

}