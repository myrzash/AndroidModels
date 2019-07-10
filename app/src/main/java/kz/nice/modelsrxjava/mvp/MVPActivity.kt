package kz.nice.modelsrxjava.mvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kz.nice.modelsrxjava.R

class MVPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        setTitle("MVP")
    }
}
