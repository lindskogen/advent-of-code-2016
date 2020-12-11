package day03

import java.io.File



fun main() {
    val directions = File("src/main/kotlin/day03/input").readLines().map {
        it.trim().split(Regex("\\s+")).map { n -> n.trim().toInt() }
    }

    println("Part 1: ${solve1(directions)}")
    println("Part 2: ${solve2(directions)}")
}

fun solve1(lines: List<List<Int>>): Int {

    return lines.count {
        val nums = it.sorted()
        nums[0] + nums[1] > nums[2]
    }
}

fun solve2(lines: List<List<Int>>): Int {
    val fstColumn = mutableListOf<Int>()
    val sndColumn = mutableListOf<Int>()
    val thdColumn = mutableListOf<Int>()

    lines.forEach {
        fstColumn.add(it[0])
        sndColumn.add(it[1])
        thdColumn.add(it[2])
    }

    return (fstColumn + sndColumn + thdColumn).chunked(3).count {
        val nums = it.sorted()
        nums[0] + nums[1] > nums[2]
    }
}
