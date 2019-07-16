package kz.nice.modelsrxjava.mvp

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.nice.modelsrxjava.model.CountriesService

class CountriesPresenter {

    private var service: CountriesService
    private var view: View

    constructor(view: View) {
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
                    view.setValues(it)
                }, {
                    view.onError()
                }
            )
    }

    fun onRefresh() {
        fetchCountries()
    }


    interface View {
        fun setValues(list: ArrayList<String>)
        fun onError()
    }
}