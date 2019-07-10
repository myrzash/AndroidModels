package kz.nice.modelsrxjava.mvvm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kz.nice.modelsrxjava.R

class MVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        setTitle("MVVM")
    }
}
