package zlc.season.bracerapp

fun Array<*>.print(): String {
    var result = "["
    forEach {
        result += "$it,"
    }
    result += "]"
    return result
}