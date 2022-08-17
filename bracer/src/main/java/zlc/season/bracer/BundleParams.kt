package zlc.season.bracer

import android.os.Bundle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

class BundleParamsDelegate<T>(
    private val bundle: Bundle,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadOnlyProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(bundle = bundle).get(key, type, defaultValue)
    }
}

class BundleMutableParamsDelegate<T>(
    private val bundle: Bundle,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(bundle = bundle).get(key, type, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val key = customKey.ifEmpty { property.name }
        Optional(bundle = bundle).put(key, type, value)
    }
}