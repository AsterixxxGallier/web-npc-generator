package character

import kotlin.random.Random

class Choice<T> {
    val options = mutableListOf<Pair<Double, () -> T>>()
    var sum = 0.0

    operator fun Number.rem(block: () -> T) {
        val p = this.toDouble() / 100.0
        options.add(p to block)
        sum += p
    }

    fun random(): T {
        var number = Random.nextDouble(sum)
        for ((p, block) in options) {
            number -= p
            if (number <= 0)
                return block()
        }
        error("Couldn't choose. This should not happen")
    }
}

fun <T> choose(block: Choice<T>.() -> Unit) =
    Choice<T>().apply(block).random()