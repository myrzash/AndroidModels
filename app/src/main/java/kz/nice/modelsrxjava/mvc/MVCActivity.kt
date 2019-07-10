package kz.nice.modelsrxjava.mvc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mvc.*
import kz.nice.modelsrxjava.R

class MVCActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<String>
    val listValues: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        setTitle("MVC")

        adapter = ArrayAdapter(this, R.layout.row_layout, R.id.textViewName, listValues)
        listViewMVC.adapter = adapter
        listViewMVC.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "clicked ${listValues.get(position)}", Toast.LENGTH_SHORT).show()
        })
        val list = arrayListOf<String>()
        for (i in 0..10) {
            list.add("USA")
            list.add("UK")
            list.add("China")
            list.add("Italy")
            list.add("Japan")
        }
        setListValues(list)
    }

    fun setListValues(values: List<String>) {
        listValues.clear()
        listValues.addAll(values)
        adapter.notifyDataSetChanged()
    }
}
