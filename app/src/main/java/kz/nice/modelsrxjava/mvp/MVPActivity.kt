package kz.nice.modelsrxjava.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mvp.*
import kz.nice.modelsrxjava.R

class MVPActivity : AppCompatActivity(), CountriesPresenter.View {

    private lateinit var presenter: CountriesPresenter
    private lateinit var adapter: ArrayAdapter<String>
    private val list: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        setTitle("MVP")

        presenter = CountriesPresenter(this)
        adapter = ArrayAdapter(this, R.layout.row_layout, R.id.textViewName, list)
        listViewMVP.adapter = adapter
        listViewMVP.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "${list.get(position)}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun setValues(values: ArrayList<String>) {
        list.clear()
        list.addAll(values)
        progress.visibility = View.GONE
        listViewMVP.visibility = View.VISIBLE
        buttonRetry.visibility = View.GONE
        adapter.notifyDataSetChanged()
    }

    override fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_message), Toast.LENGTH_SHORT).show()
        progress.visibility = View.GONE
        listViewMVP.visibility = View.GONE
        buttonRetry.visibility = View.VISIBLE
    }

    fun onRetry(view: View) {
        presenter.onRefresh()
        progress.visibility = View.VISIBLE
        listViewMVP.visibility = View.GONE
        buttonRetry.visibility = View.GONE
    }
}
