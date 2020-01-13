package zlc.season.bracerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_test.*
import zlc.season.bracer.mutableParams
import zlc.season.bracer.start
import java.math.BigDecimal

class MutableParamsActivity : AppCompatActivity() {
    var customKeyParams by mutableParams<Byte>("this is custom key")

    var customParams by mutableParams<CustomParams1>()
    var customParams2 by mutableParams<CustomParams2>()

    var parcelableParams by mutableParams<BigDecimal>(defaultValue = BigDecimal.ONE)
    var stringListParams by mutableParams<ArrayList<String>>()
    var arrayCustomParams by mutableParams<Array<CustomParams1>>()
    var intListParams by mutableParams<ArrayList<Int>>()


    var byteParams by mutableParams<Byte>(defaultValue = 1)
    var shortParams by mutableParams<Short>(defaultValue = 1)
    var intParams by mutableParams<Int>(defaultValue = 1)
    var floatParams by mutableParams<Float>(defaultValue = 1f)
    var doubleParams by mutableParams<Double>(defaultValue = 1.0)
    var longParams by mutableParams<Long>(defaultValue = 1)
    var booleanParams by mutableParams<Boolean>(defaultValue = true)
    var charParams by mutableParams<Char>(defaultValue = 'A')
    var charSequenceParams by mutableParams<CharSequence>(defaultValue = "AAA")
    var stringParams by mutableParams<String>(defaultValue = "BBB")

    var byteArrayParams by mutableParams<ByteArray>(defaultValue = ByteArray(2) { it.toByte() })
    var shortArrayParams by mutableParams<ShortArray>(defaultValue = ShortArray(2) { it.toShort() })
    var intArrayParams by mutableParams<IntArray>(defaultValue = IntArray(2) { it })
    var floatArrayParams by mutableParams<FloatArray>(defaultValue = FloatArray(2) { it.toFloat() })
    var doubleArrayParams by mutableParams<DoubleArray>(defaultValue = DoubleArray(2) { it.toDouble() })
    var longArrayParams by mutableParams<LongArray>(defaultValue = LongArray(2) { it.toLong() })
    var booleanArrayParams by mutableParams<BooleanArray>(defaultValue = BooleanArray(2) { true })
    var charArrayParams by mutableParams<CharArray>(defaultValue = CharArray(2) { 'A' })
    var charSequenceArrayParams by mutableParams<Array<CharSequence>>(
        defaultValue = Array<CharSequence>(
            2
        ) { it.toString() })
    var stringArrayParams by mutableParams<Array<String>>(defaultValue = Array<String>(2) { it.toString() })

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        startFragmentByParams()

        textView.movementMethod = ScrollingMovementMethod()
        textView.text = """
            ActivityParams:
                var customKeyParams = $customKeyParams
                var customParams = $customParams
                var customParams2 = $customParams2
                var parcelableParams = $parcelableParams
                var stringListParams = $stringListParams
                var arrayCustomParams = $arrayCustomParams
                var intListParams = $intListParams
                
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
            customParams = CustomParams1().apply {
                a = "123"
                b = 123
                c = true
            }

            stringListParams = arrayListOf("123", "123", "123")

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
