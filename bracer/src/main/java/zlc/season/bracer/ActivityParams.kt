package zlc.season.bracer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.typeOf

internal val intentsMap = mutableMapOf<Activity, Intent>()

@UseExperimental(ExperimentalStdlibApi::class)
class ActivityParamsDelegate<T : Any> : ReadWriteProperty<Activity, T> {
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return thisRef.intent.get(property)
    }

    override fun setValue(thisRef: Activity, property: KProperty<*>, value: T) {
        var intent = intentsMap[thisRef]
        if (intent == null) {
            intent = Intent()
            intentsMap[thisRef] = intent
        }

        intent.put(property.name, value)
    }
}

@ExperimentalStdlibApi
private fun <T> Intent.get(kProperty: KProperty<*>): T {
    val key = kProperty.name
    val result: Any = when (kProperty.returnType) {
        //basic type
        typeOf<Byte>() -> getByteExtra(key, 0)
        typeOf<Short>() -> getShortExtra(key, 0)
        typeOf<Int>() -> getIntExtra(key, 0)
        typeOf<Float>() -> getFloatExtra(key, 0f)
        typeOf<Double>() -> getDoubleExtra(key, 0.0)
        typeOf<Long>() -> getLongExtra(key, 0L)
        typeOf<Boolean>() -> getBooleanExtra(key, false)
        typeOf<Char>() -> getCharExtra(key, '0')
        typeOf<CharSequence>() -> getCharSequenceExtra(key) ?: ""
        typeOf<String>() -> getStringExtra(key) ?: ""

        //array type
        typeOf<ByteArray>() -> getByteArrayExtra(key) ?: ByteArray(0)
        typeOf<ShortArray>() -> getShortArrayExtra(key) ?: ShortArray(0)
        typeOf<IntArray>() -> getIntArrayExtra(key) ?: IntArray(0)
        typeOf<FloatArray>() -> getFloatArrayExtra(key) ?: FloatArray(0)
        typeOf<DoubleArray>() -> getDoubleArrayExtra(key) ?: DoubleArray(0)
        typeOf<LongArray>() -> getLongArrayExtra(key) ?: LongArray(0)
        typeOf<BooleanArray>() -> getBooleanArrayExtra(key) ?: BooleanArray(0)
        typeOf<CharArray>() -> getCharArrayExtra(key) ?: CharArray(0)
        typeOf<Array<CharSequence>>() -> getCharSequenceArrayExtra(key)
            ?: emptyArray<CharSequence>()
        typeOf<Array<String>>() -> getStringArrayExtra(key) ?: emptyArray<String>()

        //custom type
        typeOf<Bundle>() -> getBundleExtra(key) ?: Bundle()
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
    return result as T
}


private fun <T> Intent.put(key: String, value: T) {
    when (value) {
        //basic type
        is Byte -> putExtra(key, value)
        is Short -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Float -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Long -> putExtra(key, value)
        is Boolean -> putExtra(key, value)
        is Char -> putExtra(key, value)
        is CharSequence -> putExtra(key, value)
        is String -> putExtra(key, value)

        //array type
        is ByteArray -> putExtra(key, value)
        is ShortArray -> putExtra(key, value)
        is IntArray -> putExtra(key, value)
        is FloatArray -> putExtra(key, value)
        is DoubleArray -> putExtra(key, value)
        is LongArray -> putExtra(key, value)
        is BooleanArray -> putExtra(key, value)
        is CharArray -> putExtra(key, value)
        is Array<*> -> {
            when {
                value.isArrayOf<CharSequence>() -> putExtra(key, value as Array<CharSequence>)
                value.isArrayOf<String>() -> putExtra(key, value as Array<String>)
                value.isArrayOf<Parcelable>() -> putExtra(key, value as Array<Parcelable>)
                value.isArrayOf<Serializable>() -> putExtra(key, value as Array<Serializable>)
            }
        }

        //custom type
        is Bundle -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        is Serializable -> putExtra(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}