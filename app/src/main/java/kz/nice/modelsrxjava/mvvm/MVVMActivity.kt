package kz.nice.modelsrxjava.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kz.nice.modelsrxjava.R

class MVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        setTitle("MVVM")
    }
}
