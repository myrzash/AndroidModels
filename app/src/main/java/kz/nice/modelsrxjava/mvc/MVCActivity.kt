package kz.nice.modelsrxjava.mvc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mvc.*
import kz.nice.modelsrxjava.R

class MVCActivity : AppCompatActivity() {

    private lateinit var controller: CountriesController
    private lateinit var adapter: ArrayAdapter<String>
    val listValues: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        setTitle("MVC")
        controller = CountriesController(this)
        adapter = ArrayAdapter(this, R.layout.row_layout, R.id.textViewName, listValues)
        listViewMVC.adapter = adapter
        listViewMVC.setOnItemClickListener({ parent, view, position, id ->
            Toast.makeText(this, "clicked ${listValues.get(position)}", Toast.LENGTH_SHORT).show()
        })
    }

    fun setListValues(values: List<String>) {
        listValues.clear()
        listValues.addAll(values)
        progress.visibility = View.GONE
        buttonRetry.visibility = View.GONE
        listViewMVC.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    fun onRetry(view: View) {
        controller.onRefresh()
        progress.visibility = View.VISIBLE
        buttonRetry.visibility = View.GONE
        listViewMVC.visibility = View.GONE
    }

    fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_message), Toast.LENGTH_SHORT).show()
        progress.visibility = View.GONE
        buttonRetry.visibility = View.VISIBLE
        listViewMVC.visibility = View.GONE
    }
}
