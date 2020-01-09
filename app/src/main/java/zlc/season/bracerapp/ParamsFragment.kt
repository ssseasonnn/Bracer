package zlc.season.bracerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test.*
import zlc.season.bracer.params

class ParamsFragment : Fragment() {
    private val byteParams by params<Byte>()
    private val shortParams by params<Short>()
    private val intParams by params<Int>()
    private val floatParams by params<Float>()
    private val doubleParams by params<Double>()
    private val longParams by params<Long>()
    private val booleanParams by params<Boolean>()
    private val charParams by params<Char>()
    private val charSequenceParams by params<CharSequence>()
    private val stringParams by params<String>()

    private val byteArrayParams by params<ByteArray>()
    private val shortArrayParams by params<ShortArray>()
    private val intArrayParams by params<IntArray>()
    private val floatArrayParams by params<FloatArray>()
    private val doubleArrayParams by params<DoubleArray>()
    private val longArrayParams by params<LongArray>()
    private val booleanArrayParams by params<BooleanArray>()
    private val charArrayParams by params<CharArray>()
    private val charSequenceArrayParams by params<Array<CharSequence>>()
    private val stringArrayParams by params<Array<String>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.movementMethod = ScrollingMovementMethod()
        text.text = """
            FragmentParams:
                val byteP = $byteParams
                val shortP = $shortParams
                val intP = $intParams
                val floatP = $floatParams
                val doubleP = $doubleParams
                val longP = $longParams
                val booleanP = $booleanParams
                val charP = $charParams
                val charSequenceP = $charSequenceParams
                val stringP = $stringParams

                val byteArrayP = ${byteArrayParams.asList()}
                val shortArrayP = ${shortArrayParams.asList()}
                val intArrayP = ${intArrayParams.asList()}
                val floatArrayP = ${floatArrayParams.asList()}
                val doubleArrayP = ${doubleArrayParams.asList()}
                val longArrayP = ${longArrayParams.asList()}
                val booleanArrayP = ${booleanArrayParams.asList()}
                val charArrayP = ${charArrayParams.asList()}
                val charSequenceArrayP = ${charSequenceArrayParams.asList()}
                val stringArrayP = ${stringArrayParams.asList()}

        """.trimIndent()
    }
}