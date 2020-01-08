package zlc.season.bracer

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.typeOf

@UseExperimental(ExperimentalStdlibApi::class)
class FragmentParamsDelegate<T : Any> : ReadWriteProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return thisRef.arguments?.get(property) ?: Bundle().get(property)
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val arguments = thisRef.arguments ?: Bundle().also {
            thisRef.arguments = it
        }
        arguments.put(property.name, value)
    }
}

@ExperimentalStdlibApi
private fun <T> Bundle.get(kProperty: KProperty<*>): T {
    val key = kProperty.name
    val result: Any = when (kProperty.returnType) {
        //basic type
        typeOf<Byte>() -> getByte(key, 0)
        typeOf<Short>() -> getShort(key, 0)
        typeOf<Int>() -> getInt(key, 0)
        typeOf<Float>() -> getFloat(key, 0f)
        typeOf<Double>() -> getDouble(key, 0.0)
        typeOf<Long>() -> getLong(key, 0L)
        typeOf<Boolean>() -> getBoolean(key, false)
        typeOf<Char>() -> getChar(key, '0')
        typeOf<CharSequence>() -> getCharSequence(key, "")
        typeOf<String>() -> getString(key, "")

        //array type
        typeOf<ByteArray>() -> getByteArray(key) ?: ByteArray(0)
        typeOf<ShortArray>() -> getShortArray(key) ?: ShortArray(0)
        typeOf<IntArray>() -> getIntArray(key) ?: IntArray(0)
        typeOf<FloatArray>() -> getFloatArray(key) ?: FloatArray(0)
        typeOf<DoubleArray>() -> getDoubleArray(key) ?: DoubleArray(0)
        typeOf<LongArray>() -> getLongArray(key) ?: LongArray(0)
        typeOf<BooleanArray>() -> getBooleanArray(key) ?: BooleanArray(0)
        typeOf<CharArray>() -> getCharArray(key) ?: CharArray(0)
        typeOf<Array<CharSequence>>() -> getCharSequenceArray(key)
            ?: emptyArray<CharSequence>()
        typeOf<Array<String>>() -> getStringArray(key) ?: emptyArray<String>()

        //custom type
        typeOf<Bundle>() -> getBundle(key) ?: Bundle()
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
    return result as T
}

private fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        //basic type
        is Byte -> putByte(key, value)
        is Short -> putShort(key, value)
        is Int -> putInt(key, value)
        is Float -> putFloat(key, value)
        is Double -> putDouble(key, value)
        is Long -> putLong(key, value)
        is Boolean -> putBoolean(key, value)
        is Char -> putChar(key, value)
        is CharSequence -> putCharSequence(key, value)
        is String -> putString(key, value)

        //array type
        is ByteArray -> putByteArray(key, value)
        is ShortArray -> putShortArray(key, value)
        is IntArray -> putIntArray(key, value)
        is FloatArray -> putFloatArray(key, value)
        is DoubleArray -> putDoubleArray(key, value)
        is LongArray -> putLongArray(key, value)
        is BooleanArray -> putBooleanArray(key, value)
        is CharArray -> putCharArray(key, value)
        is Array<*> -> {
            if (value.isArrayOf<CharSequence>()) {
                putCharSequenceArray(key, value as Array<CharSequence>)
            } else if (value.isArrayOf<String>()) {
                putStringArray(key, value as Array<String>)
            }
        }

        //custom type
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

