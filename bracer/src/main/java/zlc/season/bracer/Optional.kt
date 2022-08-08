package zlc.season.bracer

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure

internal class Optional(
    private val intent: Intent? = null,
    private val bundle: Bundle = Bundle()
) {
    //Get methods
    fun getByte(key: String, value: Byte): Byte {
        return intent?.getByteExtra(key, value) ?: bundle.getByte(key, value)
    }

    fun getShort(key: String, value: Short): Short {
        return intent?.getShortExtra(key, value) ?: bundle.getShort(key, value)
    }

    fun getInt(key: String, value: Int): Int {
        return intent?.getIntExtra(key, value) ?: bundle.getInt(key, value)
    }

    fun getFloat(key: String, value: Float): Float {
        return intent?.getFloatExtra(key, value) ?: bundle.getFloat(key, value)
    }

    fun getDouble(key: String, value: Double): Double {
        return intent?.getDoubleExtra(key, value) ?: bundle.getDouble(key, value)
    }

    fun getLong(key: String, value: Long): Long {
        return intent?.getLongExtra(key, value) ?: bundle.getLong(key, value)
    }

    fun getBoolean(key: String, value: Boolean): Boolean {
        return intent?.getBooleanExtra(key, value) ?: bundle.getBoolean(key, value)
    }

    fun getChar(key: String, value: Char): Char {
        return intent?.getCharExtra(key, value) ?: bundle.getChar(key, value)
    }

    fun getCharSequence(key: String, value: CharSequence): CharSequence {
        return if (intent != null) {
            intent.getCharSequenceExtra(key) ?: value
        } else {
            bundle.getCharSequence(key) ?: value
        }
    }

    fun getString(key: String, value: String): String {
        return if (intent != null) {
            intent.getStringExtra(key) ?: value
        } else {
            bundle.getString(key) ?: value
        }
    }

    fun getByteArray(key: String, value: ByteArray): ByteArray {
        return if (intent != null) {
            intent.getByteArrayExtra(key) ?: value
        } else {
            bundle.getByteArray(key) ?: value
        }
    }

    fun getShortArray(key: String, value: ShortArray): ShortArray {
        return if (intent != null) {
            intent.getShortArrayExtra(key) ?: value
        } else {
            bundle.getShortArray(key) ?: value
        }
    }

    fun getIntArray(key: String, value: IntArray): IntArray {
        return if (intent != null) {
            intent.getIntArrayExtra(key) ?: value
        } else {
            bundle.getIntArray(key) ?: value
        }
    }

    fun getFloatArray(key: String, value: FloatArray): FloatArray {
        return if (intent != null) {
            intent.getFloatArrayExtra(key) ?: value
        } else {
            bundle.getFloatArray(key) ?: value
        }
    }

    fun getDoubleArray(key: String, value: DoubleArray): DoubleArray {
        return if (intent != null) {
            intent.getDoubleArrayExtra(key) ?: value
        } else {
            bundle.getDoubleArray(key) ?: value
        }
    }

    fun getLongArray(key: String, value: LongArray): LongArray {
        return if (intent != null) {
            intent.getLongArrayExtra(key) ?: value
        } else {
            bundle.getLongArray(key) ?: value
        }
    }

    fun getBooleanArray(key: String, value: BooleanArray): BooleanArray {
        return if (intent != null) {
            intent.getBooleanArrayExtra(key) ?: value
        } else {
            bundle.getBooleanArray(key) ?: value
        }
    }

    fun getCharArray(key: String, value: CharArray): CharArray {
        return if (intent != null) {
            intent.getCharArrayExtra(key) ?: value
        } else {
            bundle.getCharArray(key) ?: value
        }
    }

    fun getCharSequenceArray(key: String, value: Array<CharSequence>): Array<CharSequence> {
        return if (intent != null) {
            intent.getCharSequenceArrayExtra(key) ?: value
        } else {
            bundle.getCharSequenceArray(key) ?: value
        }
    }

    fun getStringArray(key: String, value: Array<String>): Array<String> {
        return if (intent != null) {
            intent.getStringArrayExtra(key) ?: value
        } else {
            bundle.getStringArray(key) ?: value
        }
    }

    fun getBundle(key: String, value: Bundle): Bundle {
        return if (intent != null) {
            intent.getBundleExtra(key) ?: value
        } else {
            bundle.getBundle(key) ?: value
        }
    }

    fun getJsonValue(
        key: String,
        defaultValue: Any?,
        kType: KType,
        emptyJson: String
    ): Any {
        if (intent != null) {
            val serializeString = intent.getStringExtra(key)
            return if (serializeString.isNullOrEmpty()) {
                defaultValue.or {
                    gson.fromJson(emptyJson, kType.jvmErasure.java)
                }
            } else {
                gson.fromJson(serializeString, kType.jvmErasure.java)
            }
        } else {
            val serializeString = bundle.getString(key)
            return if (serializeString.isNullOrEmpty()) {
                defaultValue.or {
                    gson.fromJson(emptyJson, kType.jvmErasure.java)
                }
            } else {
                gson.fromJson(serializeString, kType.jvmErasure.java)
            }
        }
    }

    fun getStringArrayList(key: String, value: ArrayList<String>): ArrayList<String> {
        return if (intent != null) {
            intent.getStringArrayListExtra(key) ?: value
        } else {
            bundle.getStringArrayList(key) ?: value
        }
    }

    fun getCharSequenceArrayList(
        key: String,
        value: ArrayList<CharSequence>
    ): ArrayList<CharSequence> {
        return if (intent != null) {
            intent.getCharSequenceArrayListExtra(key) ?: value
        } else {
            bundle.getCharSequenceArrayList(key) ?: value
        }
    }

    fun getIntArrayList(key: String, value: ArrayList<Int>): ArrayList<Int> {
        return if (intent != null) {
            intent.getIntegerArrayListExtra(key) ?: value
        } else {
            bundle.getIntegerArrayList(key) ?: value
        }
    }

    fun getParcelableArrayList(key: String, value: ArrayList<Parcelable>): ArrayList<Parcelable> {
        return if (intent != null) {
            intent.getParcelableArrayListExtra(key) ?: value
        } else {
            bundle.getParcelableArrayList(key) ?: value
        }
    }

    fun getParcelable(key: String, value: Any): Any {
        return if (intent != null) {
            intent.getParcelableExtra(key) ?: value
        } else {
            bundle.getParcelable(key) ?: value
        }
    }

    fun getSerializable(key: String, value: Any): Any {
        return if (intent != null) {
            intent.getSerializableExtra(key) ?: value
        } else {
            bundle.getSerializable(key) ?: value
        }
    }

    //Put methods
    fun putByte(key: String, value: Byte) {
        intent?.putExtra(key, value) ?: bundle.putByte(key, value)
    }

    fun putShort(key: String, value: Short) {
        intent?.putExtra(key, value) ?: bundle.putShort(key, value)
    }

    fun putInt(key: String, value: Int) {
        intent?.putExtra(key, value) ?: bundle.putInt(key, value)
    }

    fun putFloat(key: String, value: Float) {
        intent?.putExtra(key, value) ?: bundle.putFloat(key, value)
    }

    fun putDouble(key: String, value: Double) {
        intent?.putExtra(key, value) ?: bundle.putDouble(key, value)
    }

    fun putLong(key: String, value: Long) {
        intent?.putExtra(key, value) ?: bundle.putLong(key, value)
    }

    fun putBoolean(key: String, value: Boolean) {
        intent?.putExtra(key, value) ?: bundle.putBoolean(key, value)
    }

    fun putChar(key: String, value: Char) {
        intent?.putExtra(key, value) ?: bundle.putChar(key, value)
    }

    fun putCharSequence(key: String, value: CharSequence) {
        intent?.putExtra(key, value) ?: bundle.putCharSequence(key, value)
    }

    fun putString(key: String, value: String) {
        intent?.putExtra(key, value) ?: bundle.putString(key, value)
    }

    fun putByteArray(key: String, value: ByteArray) {
        intent?.putExtra(key, value) ?: bundle.putByteArray(key, value)
    }

    fun putShortArray(key: String, value: ShortArray) {
        intent?.putExtra(key, value) ?: bundle.putShortArray(key, value)
    }

    fun putIntArray(key: String, value: IntArray) {
        intent?.putExtra(key, value) ?: bundle.putIntArray(key, value)
    }

    fun putFloatArray(key: String, value: FloatArray) {
        intent?.putExtra(key, value) ?: bundle.putFloatArray(key, value)
    }

    fun putDoubleArray(key: String, value: DoubleArray) {
        intent?.putExtra(key, value) ?: bundle.putDoubleArray(key, value)
    }

    fun putLongArray(key: String, value: LongArray) {
        intent?.putExtra(key, value) ?: bundle.putLongArray(key, value)
    }

    fun putBooleanArray(key: String, value: BooleanArray) {
        intent?.putExtra(key, value) ?: bundle.putBooleanArray(key, value)
    }

    fun putCharArray(key: String, value: CharArray) {
        intent?.putExtra(key, value) ?: bundle.putCharArray(key, value)
    }

    fun putCharSequenceArray(key: String, value: Array<CharSequence>) {
        intent?.putExtra(key, value) ?: bundle.putCharSequenceArray(key, value)
    }

    fun putStringArray(key: String, value: Array<String>) {
        intent?.putExtra(key, value) ?: bundle.putStringArray(key, value)
    }

    fun putBundle(key: String, value: Bundle) {
        intent?.putExtra(key, value) ?: bundle.putBundle(key, value)
    }

    fun putStringArrayList(key: String, value: ArrayList<String>) {
        intent?.putStringArrayListExtra(key, value) ?: bundle.putStringArrayList(key, value)
    }

    fun putCharSequenceArrayList(
        key: String,
        value: ArrayList<CharSequence>
    ) {
        intent?.putCharSequenceArrayListExtra(key, value)
            ?: bundle.putCharSequenceArrayList(key, value)
    }

    fun putIntArrayList(key: String, value: ArrayList<Int>) {
        intent?.putIntegerArrayListExtra(key, value)
            ?: bundle.putIntegerArrayList(key, value)
    }

    fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>) {
        intent?.putParcelableArrayListExtra(key, value)
            ?: bundle.putParcelableArrayList(key, value)
    }

    fun putParcelable(key: String, value: Parcelable) {
        intent?.putExtra(key, value) ?: bundle.putParcelable(key, value)
    }

    fun putSerializable(key: String, value: Serializable) {
        intent?.putExtra(key, value) ?: bundle.putSerializable(key, value)
    }
}