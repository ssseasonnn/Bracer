package zlc.season.bracerapp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import zlc.season.bracer.params

class ParamsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {
    private val byteParams by stateHandle.params<Byte>()
    private val shortParams by stateHandle.params<Short>()
    private val intParams by stateHandle.params<Int>()
    private val floatParams by stateHandle.params<Float>()
    private val doubleParams by stateHandle.params<Double>()
    private val longParams by stateHandle.params<Long>()
    private val booleanParams by stateHandle.params<Boolean>()
    private val charParams by stateHandle.params<Char>()
    private val charSequenceParams by stateHandle.params<CharSequence>()
    private val stringParams by stateHandle.params<String>()

    private val byteArrayParams by stateHandle.params<ByteArray>()
    private val shortArrayParams by stateHandle.params<ShortArray>()
    private val intArrayParams by stateHandle.params<IntArray>()
    private val floatArrayParams by stateHandle.params<FloatArray>()
    private val doubleArrayParams by stateHandle.params<DoubleArray>()
    private val longArrayParams by stateHandle.params<LongArray>()
    private val booleanArrayParams by stateHandle.params<BooleanArray>()
    private val charArrayParams by stateHandle.params<CharArray>()
    private val charSequenceArrayParams by stateHandle.params<Array<CharSequence>>()
    private val stringArrayParams by stateHandle.params<Array<String>>()

    init {
        val log = """
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
        Log.d("TAG", log)
    }

    fun test() {
        val test by stateHandle.params<String>()
    }
}