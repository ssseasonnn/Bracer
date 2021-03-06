package zlc.season.bracer

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import android.util.Log
import com.google.gson.Gson

/**
 * Get parameters quickly from Activity's Intent
 *
 * Usage:
 *
 * class MainActivity : AppCompatActivity() {
 *      val byteParams by params<Byte>()
 * }
 *
 */
fun <T> Activity.params(customKey: String = "", defaultValue: T? = null) =
    ActivityParamsDelegate(customKey, defaultValue)

/**
 * Get parameters quickly from Fragment's Argument
 *
 * Usage:
 *
 * class TestFragment : Fragment() {
 *      val byteParams by params<Byte>()
 * }
 *
 */
fun <T> Fragment.params(customKey: String = "", defaultValue: T? = null) =
    FragmentParamsDelegate(customKey, defaultValue)

/**
 * Pass parameter to Activity quickly and
 * get parameters quickly from Activity's Intent
 *
 * Usage:
 *
 * class MainActivity : AppCompatActivity() {
 *      var byteParams by params<Byte>()
 * }
 *
 * Pass value:
 *
 * MainActivity().apply {
 *      byteParams = 1
 * }.start(context)
 *
 */
fun <T> Activity.mutableParams(customKey: String = "", defaultValue: T? = null) =
    ActivityMutableParamsDelegate(customKey, defaultValue)

/**
 * Pass parameter to Fragment quickly and
 * get parameters quickly from Fragment's Argument
 *
 * Usage:
 *
 * class TestFragment : Fragment() {
 *      var byteParams by params<Byte>()
 * }
 *
 * Pass value:
 *
 * TestFragment().apply {
 *      byteParams = 1
 * }
 *
 */
fun <T> Fragment.mutableParams(customKey: String = "", defaultValue: T? = null) =
    FragmentMutableParamsDelegate(customKey, defaultValue)

/**
 * Get intent with params
 *
 * @param context Must be activity context
 */
fun Activity.intent(context: Context): Intent {
    if (context !is Activity) throw IllegalStateException("Must use activity context!")
    val intent = intentsMap[this] ?: throw IllegalStateException("Intent not found")
    intent.setClass(context, this::class.java)
    return intent
}

/**
 * Start activity by params
 *
 * @param context Must be activity context
 */
fun Activity.start(context: Context) {
    if (context !is Activity) throw IllegalStateException("Must use activity context!")

    val intent = intentsMap[this]
    if (intent == null) {
        val newIntent = Intent(context, this::class.java)
        context.startActivity(newIntent)
    } else {
        intent.setClass(context, this::class.java)
        context.startActivity(intent)
        intentsMap.remove(this)
    }
}


internal fun log(any: Any) {
    Log.d("Bracer", any.toString())
}

internal val gson by lazy { Gson() }

internal inline fun <P, reified R> P?.or(defaultValue: () -> R): R {
    return this as? R ?: defaultValue()
}





