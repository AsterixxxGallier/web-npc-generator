package character.equipment

value class Weight(val pounds: Double)

val Number.lb get() = Weight(toDouble())