package zlc.season.bracer

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

class FragmentParamsDelegate<T>(
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadOnlyProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(bundle = thisRef.arguments ?: Bundle())
            .get(key, type, defaultValue)
    }
}

class FragmentMutableParamsDelegate<T>(
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadWriteProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(bundle = thisRef.arguments ?: Bundle())
            .get(key, type, defaultValue)
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val arguments = thisRef.arguments ?: Bundle().also {
            thisRef.arguments = it
        }
        val key = customKey.ifEmpty { property.name }
        Optional(bundle = arguments).put(key, type, value)
    }
}
