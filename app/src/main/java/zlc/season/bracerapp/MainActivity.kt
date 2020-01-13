package zlc.season.bracerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import zlc.season.bracer.start

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            ParamsActivity().apply { }.start(this)
        }

        button2.setOnClickListener {
            MutableParamsActivity().start(this)
        }
    }
}