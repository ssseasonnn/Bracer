package zlc.season.bracer

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@OptIn(ExperimentalStdlibApi::class)
class FragmentParamsDelegate<T>(
    private val customKey: String = "",
    private val defaultValue: T? = null
) : ReadOnlyProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return Optional(bundle = thisRef.arguments ?: Bundle()).get(
            customKey,
            property,
            defaultValue
        )
    }
}

@OptIn(ExperimentalStdlibApi::class)
class FragmentMutableParamsDelegate<T>(
    private val customKey: String = "",
    private val defaultValue: T? = null
) : ReadWriteProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return Optional(bundle = thisRef.arguments ?: Bundle()).get(
            customKey,
            property,
            defaultValue
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val arguments = thisRef.arguments ?: Bundle().also {
            thisRef.arguments = it
        }
        Optional(bundle = arguments).put(customKey, property, value)
    }
}
