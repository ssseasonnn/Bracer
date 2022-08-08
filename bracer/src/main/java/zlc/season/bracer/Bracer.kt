package zlc.season.bracer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
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

//Utils to start Activity.
inline fun <reified T : Activity> Activity.startActivity(vararg pair: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    intent.extras?.putAll(createBundleOf(*pair))
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.startActivity(params: T.() -> Unit) {
    val targetActivity = T::class.java.newInstance()
    targetActivity.params()

    val intent = ActivityIntentHolder.getIntent(targetActivity)
    if (intent == null) {
        val newIntent = Intent(this, T::class.java)
        startActivity(newIntent)
    } else {
        intent.setClass(this, T::class.java)
        startActivity(intent)
        ActivityIntentHolder.deleteIntent(targetActivity)
    }
}


