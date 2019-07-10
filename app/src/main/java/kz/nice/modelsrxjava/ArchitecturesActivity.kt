package kz.nice.modelsrxjava

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kz.nice.modelsrxjava.mvc.MVCActivity
import kz.nice.modelsrxjava.mvp.MVPActivity
import kz.nice.modelsrxjava.mvvm.MVVMActivity

class ArchitecturesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architectures)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.buttonMVC -> startActivity(Intent(this, MVCActivity::class.java))
            R.id.buttonMVP -> startActivity(Intent(this, MVPActivity::class.java))
            R.id.buttonMVVM -> startActivity(Intent(this, MVVMActivity::class.java))
        }
    }
}
