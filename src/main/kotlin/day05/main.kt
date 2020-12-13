package day05

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

import java.math.BigInteger
import java.security.MessageDigest

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}


@ExperimentalTime
fun main() {
    val (value, elapsed) = measureTimedValue {
        val input = "ojvtpuvg"
        println("Part 1: ${solve1(input)}")
        println("Part 2: ${solve2(input)}")
    }

    println(elapsed)
}

fun solve1(s: String): String {
    var st = ""

    var n = 0

    while (st.length < 8) {
        val res = md5(s + n)
        if (res.startsWith("00000")) {
            st += res[5]
        }
        n++
    }

    return st
}


fun solve2(s: String): String {
    val st = "_".repeat(8).toCharArray()
    var found = 0

    var n = 0

    while (found < 8) {
        val res = md5(s + n)
        if (res.startsWith("00000")) {
            if (res[5].isDigit()) {
                val index = res[5].toString().toInt()

                if (index < 8 && st[index] == '_') {

                    st[index] = res[6]
                    found++

                }
            }

        }
        n++
    }

    return st.joinToString("")
}
