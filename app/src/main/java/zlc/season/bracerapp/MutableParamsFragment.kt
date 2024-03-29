package zlc.season.bracerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import zlc.season.bracer.mutableParams
import zlc.season.bracerapp.databinding.FragmentTestBinding

class MutableParamsFragment : Fragment() {
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

    var binding: FragmentTestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTestBinding.inflate(inflater, container, false).also { this.binding = it }.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        binding.text.movementMethod = ScrollingMovementMethod()
        binding.text.text = """
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