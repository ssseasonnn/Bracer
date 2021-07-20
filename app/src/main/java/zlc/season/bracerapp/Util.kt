package zlc.season.bracerapp

import java.io.Serializable

val listParam = listOf("AAA", "BBB", "CCC")
val arrayParam = arrayOf("AAA", "BBB", "CCC")
val mapParam = mapOf(
    "A" to "AAA",
    "B" to "BBB",
    "C" to "CCC"
)

data class CustomParams1(
    var a: String = "",
    var b: Int = 0,
    var c: Boolean = false
) : Serializable {
    override fun toString(): String {
        return """{a=$a,b=$b,c=$c}"""
    }
}

class CustomParams2 : Serializable {
    var str: String = ""
    var int: Int = 0
    var boolean: Boolean = false
    var list: List<String> = emptyList()
    var map: Map<String, String> = emptyMap()
    var array: Array<String> = emptyArray()

    override fun toString(): String {
        return """
            {str=$str,int=$int,boolean=$boolean,list=$list,map=$map,array=${array.asList()}}
            """.trimIndent()
    }
}