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

class TestFragment : Fragment() {
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
                var byteP = $byteParams
                var shortP = $shortParams
                var intP = $intParams
                var floatP = $floatParams
                var doubleP = $doubleParams
                var longP = $longParams
                var booleanP = $booleanParams
                var charP = $charParams
                var charSequenceP = $charSequenceParams
                var stringP = $stringParams

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
    }
}