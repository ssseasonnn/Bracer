package zlc.season.bracerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zlc.season.bracer.startActivity
import zlc.season.bracerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity<ParamsActivity> {
                customParams = CustomParams1()
            }
        }

        binding.button2.setOnClickListener {
            startActivity<MutableParamsActivity> {
                byteParams = 1
                intParams = 123
            }
        }
    }
}