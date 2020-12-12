package day06

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue


@ExperimentalTime
fun main() {
    val (value, elapsed) = measureTimedValue {
        val codes = File("src/main/kotlin/day06/input").readLines()

        val mapList = codes[0].indices.map { index ->
            codes.groupingBy { it[index] }.eachCount()
        }

        println("Part 1: ${solve1(mapList)}")
        println("Part 2: ${solve2(mapList)}")
    }

    println(elapsed)
}

fun solve1(mapList: List<Map<Char, Int>>): String {
    return mapList.map { charCount ->
        charCount.maxByOrNull { it.value }!!.key
    }.joinToString("")
}

fun solve2(mapList: List<Map<Char, Int>>): String {
    return mapList.map { charCount ->
        charCount.minByOrNull { it.value }!!.key
    }.joinToString("")
}
