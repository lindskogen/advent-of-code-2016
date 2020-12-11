package day01

import java.io.File
import kotlin.math.abs

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    fun turnLeft(): Direction {
        return when (this) {
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
            NORTH -> WEST
        }
    }

    fun turnRight(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}

fun main() {
    val directions = File("src/main/kotlin/day01/input").readText().trim().split(", ")

    println("Part 1: ${solve(directions, false)}")
    println("Part 2: ${solve(directions, true)}")
}

fun solve(directions: List<String>, stopOnDuplicate: Boolean): Int {
    var dir = Direction.NORTH
    var x = 0
    var y = 0

    val set = mutableSetOf(Pair(x, y))

    for (d in directions) {
        val steps = d.substring(1).toInt()
        when (d[0]) {
            'R' -> {
                dir = dir.turnRight()
            }
            'L' -> {
                dir = dir.turnLeft()
            }
        }

        for (s in 1..steps) {
            when (dir) {
                Direction.NORTH -> {
                    y += 1
                }
                Direction.EAST -> {
                    x += 1
                }
                Direction.SOUTH -> {
                    y -= 1
                }
                Direction.WEST -> {
                    x -= 1
                }
            }

            if (stopOnDuplicate && !set.add(Pair(x, y))) {
                return subAbs(x, y)
            }
        }
    }

    return subAbs(x, y)
}

private fun subAbs(x: Int, y: Int) = abs(x) + abs(y)
