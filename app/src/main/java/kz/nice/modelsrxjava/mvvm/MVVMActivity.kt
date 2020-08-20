package kz.nice.modelsrxjava.mvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mvvm.*
import kz.nice.modelsrxjava.R

class MVVMActivity : AppCompatActivity() {
    private lateinit var viewModel: CountriesViewModel
    private val listValues = ArrayList<String>()
    private lateinit var listAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        title = "MVVM"

        listAdapter =
            ArrayAdapter(this, R.layout.row_layout, R.id.textViewName, listValues)
        listViewMVVM.adapter = listAdapter
        listViewMVVM.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "clicked ${listValues.get(position)}", Toast.LENGTH_SHORT).show()
        }

        viewModel = ViewModelProviders.of(this)
            .get(CountriesViewModel::class.java)
        observeViewModel()
    }

    fun onRetry(view: View) {
        viewModel.onRefresh()
        listViewMVVM.visibility = View.GONE
        progress.visibility = View.VISIBLE
        buttonRetry.visibility = View.GONE
    }

    private fun observeViewModel() {
        viewModel.getCountries().observe(this, Observer<List<String>> { countries ->
            if (countries != null) {
                listValues.clear()
                listValues.addAll(countries)
                listViewMVVM.visibility = View.VISIBLE
                listAdapter.notifyDataSetChanged()
            } else {
                listViewMVVM.visibility = View.GONE
            }
        })

        viewModel.getCountryError().observe(this, Observer<Boolean> { error ->
            progress.visibility = View.GONE
            if (error!!) {
                Toast.makeText(this, "Error $error", Toast.LENGTH_SHORT).show()
                buttonRetry.visibility = View.VISIBLE
            } else {
                buttonRetry.visibility = View.GONE
            }
        })
    }
}
