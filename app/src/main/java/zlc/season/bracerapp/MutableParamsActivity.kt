package zlc.season.bracerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_test.*
import zlc.season.bracer.mutableParams
import zlc.season.bracer.start

class MutableParamsActivity : AppCompatActivity() {
    var customKeyParams by mutableParams<Byte>("this is custom key")

    var byteParams by mutableParams<Byte>()
    var shortParams by mutableParams<Short>()
    var intParams by mutableParams<Int>()
    var floatParams by mutableParams<Float>()
    var doubleParams by mutableParams<Double>()
    var longParams by mutableParams<Long>()
    var booleanParams by mutableParams<Boolean>()
    var charParams by mutableParams<Char>()
    var charSequenceParams by mutableParams<CharSequence>()
    var stringParams by mutableParams<String>()

    var byteArrayParams by mutableParams<ByteArray>()
    var shortArrayParams by mutableParams<ShortArray>()
    var intArrayParams by mutableParams<IntArray>()
    var floatArrayParams by mutableParams<FloatArray>()
    var doubleArrayParams by mutableParams<DoubleArray>()
    var longArrayParams by mutableParams<LongArray>()
    var booleanArrayParams by mutableParams<BooleanArray>()
    var charArrayParams by mutableParams<CharArray>()
    var charSequenceArrayParams by mutableParams<Array<CharSequence>>()
    var stringArrayParams by mutableParams<Array<String>>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        startFragmentByParams()

        textView.movementMethod = ScrollingMovementMethod()
        textView.text = """
            ActivityParams:
                var customKeyParams = $customKeyParams
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
        btnDialog.setOnClickListener {
            showDialogByParams()
        }
    }

    private fun showDialogByParams() {
        MutableParamsDialog().apply {
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
        }.show(supportFragmentManager, "")
    }

    private fun startActivityByParams() {
        MutableParamsActivity().apply {
            customKeyParams = 1

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
        val fragment = MutableParamsFragment().apply {
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
