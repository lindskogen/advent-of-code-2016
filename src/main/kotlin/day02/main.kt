package day02

import java.io.File


typealias Keypad = List<List<Char?>>

fun main() {
    val directions = File("src/main/kotlin/day02/input").readLines()

    println("Part 1: ${solve1(directions)}")
    println("Part 2: ${solve2(directions)}")
}

fun solve1(lines: List<String>): String {
    val keypad: Keypad = listOf(
        listOf('1', '2', '3'),
        listOf('4', '5', '6'),
        listOf('7', '8', '9'),
    )

    return walkKeypad(lines, keypad)
}

fun solve2(lines: List<String>): String {
    val keypad: Keypad = listOf(
        listOf(null, null, '1', null, null),
        listOf(null, '2', '3', '4', null),
        listOf('5', '6', '7', '8', '9'),
        listOf(null, 'A', 'B', 'C', null),
        listOf(null, null, 'D', null, null),
    )

    return walkKeypad(lines, keypad)
}

private fun walkKeypad(
    lines: List<String>,
    keypad: Keypad
): String {
    var x = 1
    var y = 1

    var code = ""

    for (line in lines) {
        for (c in line) {
            when (c) {
                'U' -> {
                    if (isInsideTheKeypad(keypad, y - 1, x)) {
                        y--
                    }
                }
                'D' -> {
                    if (isInsideTheKeypad(keypad, y + 1, x)) {
                        y++
                    }
                }
                'R' -> {
                    if (isInsideTheKeypad(keypad, y, x + 1)) {
                        x++
                    }
                }
                'L' -> {
                    if (isInsideTheKeypad(keypad, y, x - 1)) {
                        x--
                    }
                }
            }
        }
        code += keypad[y][x]
    }

    return code
}

private fun isInsideTheKeypad(
    keypad: Keypad,
    y: Int,
    x: Int
) = keypad.elementAtOrNull(y)?.elementAtOrNull(x) != null
