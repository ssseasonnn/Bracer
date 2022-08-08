package zlc.season.bracer

import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Log
import android.util.Size
import android.util.SizeF
import com.google.gson.Gson
import java.io.Serializable
import kotlin.reflect.KType
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.typeOf

internal inline fun <P, reified R> P?.or(defaultValue: () -> R): R {
    return this as? R ?: defaultValue()
}

internal fun log(any: Any) {
    Log.d("Bracer", any.toString())
}

internal val gson by lazy { Gson() }

private fun KType.createObj() = try {
    jvmErasure.createInstance()
} catch (e: Throwable) {
    gson.fromJson("{}", jvmErasure.java)
}

@Suppress("UNCHECKED_CAST")
internal fun <T> KType.defaultValue(): T {
    val result: Any = when (this) {
        //basic type
        typeOf<Byte>() -> 0.toByte()
        typeOf<Short>() -> 0.toShort()
        typeOf<Int>() -> 0
        typeOf<Float>() -> 0f
        typeOf<Double>() -> 0.0
        typeOf<Long>() -> 0L
        typeOf<Boolean>() -> false
        typeOf<Char>() -> '0'
        typeOf<CharSequence>() -> ""
        typeOf<String>() -> ""

        //array type
        typeOf<ByteArray>() -> ByteArray(0)
        typeOf<ShortArray>() -> ShortArray(0)
        typeOf<IntArray>() -> IntArray(0)
        typeOf<FloatArray>() -> FloatArray(0)
        typeOf<DoubleArray>() -> DoubleArray(0)
        typeOf<LongArray>() -> LongArray(0)
        typeOf<BooleanArray>() -> BooleanArray(0)
        typeOf<CharArray>() -> CharArray(0)
        typeOf<Array<CharSequence>>() -> emptyArray<CharSequence>()
        typeOf<Array<String>>() -> emptyArray<String>()

        //custom type
        typeOf<Bundle>() -> Bundle()

        else -> {
            when {
                /**
                 * Must check array and list first!!
                 */
                isSubtypeOf(typeOf<Array<*>>()) -> {
                    gson.fromJson("[]", jvmErasure.java)
                }
                isSubtypeOf(typeOf<List<*>>()) -> {
                    when {
                        isSubtypeOf(typeOf<ArrayList<String>>()) -> {
                            arrayListOf<String>()
                        }
                        isSubtypeOf(typeOf<ArrayList<CharSequence>>()) -> {
                            arrayListOf<CharSequence>()
                        }
                        isSubtypeOf(typeOf<ArrayList<Int>>()) -> {
                            arrayListOf<Int>()
                        }
                        isSubtypeOf(typeOf<ArrayList<Parcelable>>()) -> {
                            arrayListOf<Parcelable>()
                        }
                        else -> {
                            gson.fromJson("[]", jvmErasure.java)
                        }
                    }
                }
                isSubtypeOf(typeOf<Parcelable>()) -> createObj()
                isSubtypeOf(typeOf<Serializable>()) -> createObj()
                else -> {
                    gson.fromJson("{}", jvmErasure.java)
                }
            }
        }
    }
    return result as T
}


@Suppress("UNCHECKED_CAST")
internal fun <T> Optional.get(key: String, kType: KType, defaultValue: T?): T {
    val result: Any = when (kType) {
        //basic type
        typeOf<Byte>() -> getByte(key, defaultValue.or { 0.toByte() })
        typeOf<Short>() -> getShort(key, defaultValue.or { 0.toShort() })
        typeOf<Int>() -> getInt(key, defaultValue.or { 0 })
        typeOf<Float>() -> getFloat(key, defaultValue.or { 0f })
        typeOf<Double>() -> getDouble(key, defaultValue.or { 0.0 })
        typeOf<Long>() -> getLong(key, defaultValue.or { 0L })
        typeOf<Boolean>() -> getBoolean(key, defaultValue.or { false })
        typeOf<Char>() -> getChar(key, defaultValue.or { '0' })
        typeOf<CharSequence>() -> getCharSequence(key, defaultValue.or { "" })
        typeOf<String>() -> getString(key, defaultValue.or { "" })

        //array type
        typeOf<ByteArray>() -> getByteArray(key, defaultValue.or { ByteArray(0) })
        typeOf<ShortArray>() -> getShortArray(key, defaultValue.or { ShortArray(0) })
        typeOf<IntArray>() -> getIntArray(key, defaultValue.or { IntArray(0) })
        typeOf<FloatArray>() -> getFloatArray(key, defaultValue.or { FloatArray(0) })
        typeOf<DoubleArray>() -> getDoubleArray(key, defaultValue.or { DoubleArray(0) })
        typeOf<LongArray>() -> getLongArray(key, defaultValue.or { LongArray(0) })
        typeOf<BooleanArray>() -> getBooleanArray(key, defaultValue.or { BooleanArray(0) })
        typeOf<CharArray>() -> getCharArray(key, defaultValue.or { CharArray(0) })
        typeOf<Array<CharSequence>>() -> getCharSequenceArray(key, defaultValue.or { emptyArray() })
        typeOf<Array<String>>() -> getStringArray(key, defaultValue.or { emptyArray() })

        //custom type
        typeOf<Bundle>() -> getBundle(key, defaultValue.or { Bundle() })

        else -> {
            when {
                /**
                 * Must check array and list first!!
                 */
                kType.isSubtypeOf(typeOf<Array<*>>()) -> {
                    getJsonValue(key, defaultValue, kType, "[]")
                }
                kType.isSubtypeOf(typeOf<List<*>>()) -> {
                    when {
                        kType.isSubtypeOf(typeOf<ArrayList<String>>()) -> {
                            getStringArrayList(key, defaultValue.or { arrayListOf() })
                        }
                        kType.isSubtypeOf(typeOf<ArrayList<CharSequence>>()) -> {
                            getCharSequenceArrayList(key, defaultValue.or { arrayListOf() })
                        }
                        kType.isSubtypeOf(typeOf<ArrayList<Int>>()) -> {
                            getIntArrayList(key, defaultValue.or { arrayListOf() })
                        }
                        kType.isSubtypeOf(typeOf<ArrayList<Parcelable>>()) -> {
                            getParcelableArrayList(key, defaultValue.or { arrayListOf() })
                        }
                        else -> {
                            getJsonValue(key, defaultValue, kType, "[]")
                        }
                    }
                }
                kType.isSubtypeOf(typeOf<Parcelable>()) -> {
                    getParcelable(key, defaultValue.or { kType.createObj() })
                }
                kType.isSubtypeOf(typeOf<Serializable>()) -> {
                    getSerializable(key, defaultValue.or { kType.createObj() })
                }
                else -> {
                    getJsonValue(key, defaultValue, kType, "{}")
                }
            }
        }
    }
    return result as T
}

@Suppress("UNCHECKED_CAST")
internal fun <T> Optional.put(key: String, kType: KType, value: T) {
    when (kType) {
        //basic type
        typeOf<Byte>() -> putByte(key, value as Byte)
        typeOf<Short>() -> putShort(key, value as Short)
        typeOf<Int>() -> putInt(key, value as Int)
        typeOf<Float>() -> putFloat(key, value as Float)
        typeOf<Double>() -> putDouble(key, value as Double)
        typeOf<Long>() -> putLong(key, value as Long)
        typeOf<Boolean>() -> putBoolean(key, value as Boolean)
        typeOf<Char>() -> putChar(key, value as Char)
        typeOf<CharSequence>() -> putCharSequence(key, value as CharSequence)
        typeOf<String>() -> putString(key, value as String)

        //array type
        typeOf<ByteArray>() -> putByteArray(key, value as ByteArray)
        typeOf<ShortArray>() -> putShortArray(key, value as ShortArray)
        typeOf<IntArray>() -> putIntArray(key, value as IntArray)
        typeOf<FloatArray>() -> putFloatArray(key, value as FloatArray)
        typeOf<DoubleArray>() -> putDoubleArray(key, value as DoubleArray)
        typeOf<LongArray>() -> putLongArray(key, value as LongArray)
        typeOf<BooleanArray>() -> putBooleanArray(key, value as BooleanArray)
        typeOf<CharArray>() -> putCharArray(key, value as CharArray)
        typeOf<Array<CharSequence>>() -> putCharSequenceArray(key, value as Array<CharSequence>)
        typeOf<Array<String>>() -> putStringArray(key, value as Array<String>)

        //custom type
        typeOf<Bundle>() -> putBundle(key, value as Bundle)

        else -> {
            when {
                /**
                 * Must check array and list first!!
                 */
                kType.isSubtypeOf(typeOf<Array<*>>()) -> {
                    putString(key, gson.toJson(value))
                }
                kType.isSubtypeOf(typeOf<List<*>>()) -> {
                    when {
                        kType.isSubtypeOf(typeOf<ArrayList<String>>()) -> {
                            putStringArrayList(key, value as ArrayList<String>)
                        }
                        kType.isSubtypeOf(typeOf<ArrayList<CharSequence>>()) -> {
                            putCharSequenceArrayList(key, value as ArrayList<CharSequence>)
                        }
                        kType.isSubtypeOf(typeOf<ArrayList<Int>>()) -> {
                            putIntArrayList(key, value as ArrayList<Int>)
                        }
                        kType.isSubtypeOf(typeOf<ArrayList<Parcelable>>()) -> {
                            putParcelableArrayList(key, value as ArrayList<out Parcelable>)
                        }
                        else -> {
                            putString(key, gson.toJson(value))
                        }
                    }
                }
                kType.isSubtypeOf(typeOf<Parcelable>()) -> {
                    putParcelable(key, value as Parcelable)
                }
                kType.isSubtypeOf(typeOf<Serializable>()) -> {
                    putSerializable(key, value as Serializable)
                }
                else -> {
                    putString(key, gson.toJson(value))
                }
            }
        }
    }
}

fun createBundleOf(vararg pairs: Pair<String, Any?>): Bundle = Bundle(pairs.size).apply {
    for ((key, value) in pairs) {
        when (value) {
            null -> putString(key, null) // Any nullable type will suffice.

            // Scalars
            is Boolean -> putBoolean(key, value)
            is Byte -> putByte(key, value)
            is Char -> putChar(key, value)
            is Double -> putDouble(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Short -> putShort(key, value)

            // References
            is Bundle -> putBundle(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Parcelable -> putParcelable(key, value)

            // Scalar arrays
            is BooleanArray -> putBooleanArray(key, value)
            is ByteArray -> putByteArray(key, value)
            is CharArray -> putCharArray(key, value)
            is DoubleArray -> putDoubleArray(key, value)
            is FloatArray -> putFloatArray(key, value)
            is IntArray -> putIntArray(key, value)
            is LongArray -> putLongArray(key, value)
            is ShortArray -> putShortArray(key, value)

            // Reference arrays
            is Array<*> -> {
                val componentType = value::class.java.componentType!!
                @Suppress("UNCHECKED_CAST") // Checked by reflection.
                when {
                    Parcelable::class.java.isAssignableFrom(componentType) -> {
                        putParcelableArray(key, value as Array<Parcelable>)
                    }
                    String::class.java.isAssignableFrom(componentType) -> {
                        putStringArray(key, value as Array<String>)
                    }
                    CharSequence::class.java.isAssignableFrom(componentType) -> {
                        putCharSequenceArray(key, value as Array<CharSequence>)
                    }
                    Serializable::class.java.isAssignableFrom(componentType) -> {
                        putSerializable(key, value)
                    }
                    else -> {
                        val valueType = componentType.canonicalName
                        throw IllegalArgumentException(
                            "Illegal value array type $valueType for key \"$key\""
                        )
                    }
                }
            }

            // Last resort. Also we must check this after Array<*> as all arrays are serializable.
            is Serializable -> putSerializable(key, value)

            else -> {
                if (Build.VERSION.SDK_INT >= 18 && value is IBinder) {
                    putBinder(key, value)
                } else if (Build.VERSION.SDK_INT >= 21 && value is Size) {
                    putSize(key, value)
                } else if (Build.VERSION.SDK_INT >= 21 && value is SizeF) {
                    putSizeF(key, value)
                } else {
                    val valueType = value.javaClass.canonicalName
                    throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
                }
            }
        }
    }
}
