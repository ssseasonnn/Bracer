package zlc.season.bracerapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_test.*
import zlc.season.bracer.params

class ParamsActivity : AppCompatActivity() {
    private val customKeyParams by params<Byte>("this is custom key")

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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        startFragmentNormal()

        textView.movementMethod = ScrollingMovementMethod()
        textView.text = """
            ActivityParmas:
                 val customKeyParams = $customKeyParams
                 val byteP = $byteParams
                 val shortP = $shortParams
                 val intP = $intParams
                 val floatP = $floatParams
                 val doubleP = $doubleParams
                 val longP = $longParams
                 val booleanP = $booleanParams
                 val charP = $charParams
                 val charSequenceP = $charSequenceParams
                 val stringParams = $stringParams

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


        btnNext.setOnClickListener {
            startActivityNormal()
        }

        btnDialog.setOnClickListener {
            showDialogNormal()
        }
    }

    private fun showDialogNormal() {
        val dialog = ParamsDialog()
        val bundle = Bundle()
        bundle.putByte("byteParams", 1)
        dialog.arguments = bundle
        dialog.show(supportFragmentManager, "")
    }

    private fun startActivityNormal() {
        val intent = Intent(this, ParamsActivity::class.java)
        intent.putExtra("byteParams", 1.toByte())
        intent.putExtra("this is custom key", 123.toByte())
        startActivity(intent)
    }

    private fun startFragmentNormal() {
        val fragment = ParamsFragment()
        val bundle = Bundle()
        bundle.putByte("byteParams", 1)
        fragment.arguments = bundle

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.frameLayout, fragment, "")
        beginTransaction.commit()
    }
}
