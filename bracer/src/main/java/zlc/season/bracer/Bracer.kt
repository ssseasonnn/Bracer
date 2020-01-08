package zlc.season.bracer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.util.Log

fun <T : Any> Activity.params() = ActivityParamsDelegate<T>()

fun <T : Any> Fragment.params() = FragmentParamsDelegate<T>()


/**
 * Get intent with params
 *
 * @param context Must be activity context
 */
fun Activity.intent(context: Context): Intent {
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
    val intent = intentsMap[this] ?: throw IllegalStateException("Intent not found")
    intent.setClass(context, this::class.java)
    context.startActivity(intent)
    intentsMap.remove(this)
}

internal fun log(any: Any) {
    Log.d("Bracer", any.toString())
}






