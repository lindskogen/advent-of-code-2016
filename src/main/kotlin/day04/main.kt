package day04

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

private fun decrypt(name: String, sectorId: Int): String {
    val aInt = 97

    val shift = sectorId % 26

    return name.map {
        if (it == '-') {
            ' '
        } else {
            val letterNo = it.toInt() - aInt

            (aInt + ((letterNo + shift) % 26)).toChar()
        }
    }.joinToString("")
}

data class Room(val encryptedName: String, val sectorId: Int, val checksum: String) {

    fun decryptName(): String {
        return decrypt(encryptedName, sectorId)
    }

    fun matchesChecksum(): Boolean {
        val allChars = encryptedName.replace("-", "").toCharArray().toList().sorted()

        val map = mutableMapOf<Char, Int>()

        allChars.forEach {
            map.compute(it) { _, i -> (i ?: 0) + 1 }
        }

        val calculatedChecksum = map.toList().sortedWith { (a, ai), (b, bi) ->
            if (bi == ai) {
                a - b
            } else {
                bi - ai
            }
        }.take(5).map { it.first }.joinToString("")

        return checksum == calculatedChecksum
    }

    companion object {
        fun parse(it: String): Room {
            val parts = it.dropLast(7).split("-")

            val name = parts.dropLast(1).joinToString("-")
            val sectorId = parts.last().toInt()
            val checksum = it.takeLast(7).substring(1, 6)

            return Room(name, sectorId, checksum)
        }
    }
}

@ExperimentalTime
fun main() {
    val (value, elapsed) = measureTimedValue {
        val rooms = File("src/main/kotlin/day04/input").readLines().map(Room::parse)

        val realRooms = rooms.filter { it.matchesChecksum() }

        println("Part 1: ${solve1(realRooms)}")
        println("Part 2: ${solve2(realRooms)}")
    }

    println(elapsed)
}

private fun solve1(rooms: List<Room>) = rooms.sumOf { it.sectorId }

fun solve2(rooms: List<Room>): Int? {
    val room = rooms.find {
        it.decryptName() == "northpole object storage"
    }

    return room?.sectorId
}
