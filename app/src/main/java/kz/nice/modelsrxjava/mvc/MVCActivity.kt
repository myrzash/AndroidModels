package kz.nice.modelsrxjava.mvc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kz.nice.modelsrxjava.R

class MVCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        setTitle("MVC")
    }
}
