package kz.nice.modelsrxjava.mvc

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.nice.modelsrxjava.model.CountriesService

class CountriesController {

    var view: MVCActivity
    var service: CountriesService

    constructor(view: MVCActivity) {
        this.view = view
        this.service = CountriesService()
        fetchCountries()
    }

    private fun fetchCountries() {
        service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .map {
                val list = ArrayList<String>()
                for (country in it) {
                    list.add(country.name.toString())
                }
                list
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.setListValues(it)
                },
                {
                    Log.w("LOG_TAG", "Error: $it")
                    view.onError()
                }
            )
    }


    fun onRefresh() {
        this.fetchCountries()
    }
}