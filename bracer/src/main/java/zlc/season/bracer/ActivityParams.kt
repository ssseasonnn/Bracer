package zlc.season.bracer

import android.app.Activity
import android.content.Intent
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

internal val intentsMap = mutableMapOf<Activity, Intent>()

class ActivityParamsDelegate<T>(
    private val activity: Activity,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(activity.intent).get(key, type, defaultValue)
    }
}

class ActivityMutableParamsDelegate<T>(
    private val activity: Activity,
    private val customKey: String = "",
    private val defaultValue: T? = null,
    private val type: KType
) : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val key = customKey.ifEmpty { property.name }
        return Optional(activity.intent).get(key, type, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        var intent = intentsMap[thisRef]
        if (intent == null) {
            intent = Intent()
            intentsMap[activity] = intent
        }

        val key = customKey.ifEmpty { property.name }
        Optional(intent).put(key, type, value)
    }
}