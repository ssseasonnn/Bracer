package zlc.season.bracer

import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

class StateHandleParamsDelegate<T>(
    private val savedStateHandle: SavedStateHandle,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return savedStateHandle.get(key) ?: (defaultValue ?: type.defaultValue())
    }
}

class StateHandleMutableParamsDelegate<T>(
    private val savedStateHandle: SavedStateHandle,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return savedStateHandle.get(key) ?: (defaultValue ?: type.defaultValue())
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val key = customKey.ifEmpty { property.name }
        savedStateHandle.set(key, value)
    }
}