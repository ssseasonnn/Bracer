package zlc.season.bracerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zlc.season.bracer.start
import zlc.season.bracerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            ParamsActivity().apply { }.start(this)
        }

        binding.button2.setOnClickListener {
            MutableParamsActivity().start(this)
        }
    }
}