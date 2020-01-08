package zlc.season.bracerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_main.*
import zlc.season.bracer.params
import zlc.season.bracer.start

class MainActivity : AppCompatActivity() {
    var byteParams by params<Byte>()
    var shortParams by params<Short>()
    var intParams by params<Int>()
    var floatParams by params<Float>()
    var doubleParams by params<Double>()
    var longParams by params<Long>()
    var booleanParams by params<Boolean>()
    var charParams by params<Char>()
    var charSequenceParams by params<CharSequence>()
    var stringParams by params<String>()

    var byteArrayParams by params<ByteArray>()
    var shortArrayParams by params<ShortArray>()
    var intArrayParams by params<IntArray>()
    var floatArrayParams by params<FloatArray>()
    var doubleArrayParams by params<DoubleArray>()
    var longArrayParams by params<LongArray>()
    var booleanArrayParams by params<BooleanArray>()
    var charArrayParams by params<CharArray>()
    var charSequenceArrayParams by params<Array<CharSequence>>()
    var stringArrayParams by params<Array<String>>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragmentByParams()

        textView.movementMethod = ScrollingMovementMethod()
        textView.text = """
            ActivityParmas:
                var byteP = $byteParams
                var shortP = $shortParams
                var intP = $intParams
                var floatP = $floatParams
                var doubleP = $doubleParams
                var longP = $longParams
                var booleanP = $booleanParams
                var charP = $charParams
                var charSequenceP = $charSequenceParams
                var stringParams = $stringParams

                var byteArrayP = ${byteArrayParams.asList()}
                var shortArrayP = ${shortArrayParams.asList()}
                var intArrayP = ${intArrayParams.asList()}
                var floatArrayP = ${floatArrayParams.asList()}
                var doubleArrayP = ${doubleArrayParams.asList()}
                var longArrayP = ${longArrayParams.asList()}
                var booleanArrayP = ${booleanArrayParams.asList()}
                var charArrayP = ${charArrayParams.asList()}
                var charSequenceArrayP = ${charSequenceArrayParams.asList()}
                var stringArrayP = ${stringArrayParams.asList()}

        """.trimIndent()


        btnNext.setOnClickListener {
            startActivityByParams()
        }
    }

    private fun startActivityByParams() {
        MainActivity().apply {
            byteParams = 1
            shortParams = 1
            intParams = 1
            floatParams = 1f
            doubleParams = 1.0
            longParams = 1L
            booleanParams = true
            charParams = '1'
            charSequenceParams = "111"
            stringParams = "111"

            byteArrayParams = byteArrayOf(1, 2, 3)
            shortArrayParams = shortArrayOf(1, 2, 3)
            intArrayParams = intArrayOf(1, 2, 3)
            floatArrayParams = floatArrayOf(1f, 2f, 3f)
            doubleArrayParams = doubleArrayOf(1.0, 2.0, 3.0)
            longArrayParams = longArrayOf(1L, 2L, 3L)
            booleanArrayParams = booleanArrayOf(true, false, true)
            charArrayParams = charArrayOf('1', '2', '3')
            charSequenceArrayParams = Array(3) { "$it" }
            stringArrayParams = Array(3) { "$it" }
        }.start(this)
    }

    private fun startFragmentByParams() {
        val fragment = TestFragment().apply {
            byteParams = 1
            shortParams = 1
            intParams = 1
            floatParams = 1f
            doubleParams = 1.0
            longParams = 1L
            booleanParams = true
            charParams = '1'
            charSequenceParams = "111"
            stringParams = "111"

            byteArrayParams = byteArrayOf(1, 2, 3)
            shortArrayParams = shortArrayOf(1, 2, 3)
            intArrayParams = intArrayOf(1, 2, 3)
            floatArrayParams = floatArrayOf(1f, 2f, 3f)
            doubleArrayParams = doubleArrayOf(1.0, 2.0, 3.0)
            longArrayParams = longArrayOf(1L, 2L, 3L)
            booleanArrayParams = booleanArrayOf(true, false, true)
            charArrayParams = charArrayOf('1', '2', '3')
            charSequenceArrayParams = Array(3) { "$it" }
            stringArrayParams = Array(3) { "$it" }
        }

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.frameLayout, fragment, "")
        beginTransaction.commit()
    }
}
