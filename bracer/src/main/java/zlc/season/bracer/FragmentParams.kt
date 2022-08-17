package zlc.season.bracer

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

class FragmentParamsDelegate<T>(
    private val fragment: Fragment,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadOnlyProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(bundle = fragment.arguments ?: Bundle())
            .get(key, type, defaultValue)
    }
}

class FragmentMutableParamsDelegate<T>(
    private val fragment: Fragment,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(bundle = fragment.arguments ?: Bundle())
            .get(key, type, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val arguments = fragment.arguments ?: Bundle().also {
            fragment.arguments = it
        }
        val key = customKey.ifEmpty { property.name }
        Optional(bundle = arguments).put(key, type, value)
    }
}
