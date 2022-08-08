package zlc.season.bracer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlin.reflect.typeOf

inline fun <reified T> Activity.params(
    customKey: String = "",
    defaultValue: T? = null
) = ActivityParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> Fragment.params(
    customKey: String = "",
    defaultValue: T? = null
) = FragmentParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> SavedStateHandle.params(
    customKey: String = "",
    defaultValue: T? = null
) = StateHandleParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> Intent.params(
    customKey: String = "",
    defaultValue: T? = null
) = IntentParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> Bundle.params(
    customKey: String = "",
    defaultValue: T? = null
) = BundleParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> Activity.mutableParams(
    customKey: String = "",
    defaultValue: T? = null
) = ActivityMutableParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> Fragment.mutableParams(
    customKey: String = "",
    defaultValue: T? = null
) = FragmentMutableParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> SavedStateHandle.mutableParams(
    customKey: String = "",
    defaultValue: T? = null
) = StateHandleMutableParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> Intent.mutableParams(
    customKey: String = "",
    defaultValue: T? = null
) = IntentMutableParamsDelegate(this, customKey, defaultValue, typeOf<T>())

inline fun <reified T> Bundle.mutableParams(
    customKey: String = "",
    defaultValue: T? = null
) = BundleMutableParamsDelegate(this, customKey, defaultValue, typeOf<T>())


/**
 * Get intent with params
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


inline fun <reified T : Activity> Activity.startActivity(vararg pair: Pair<Any, Any>) {
    val intent = Intent(this, T::class.java)
//    intent.putExtra(Bundle().putAll(pair))
}


