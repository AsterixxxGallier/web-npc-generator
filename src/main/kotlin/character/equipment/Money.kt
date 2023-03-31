package character.equipment

value class Money(val copperWorth: Int)

val Int.cp get() = Money(this)
val Int.sp get() = Money(this * 10)
val Int.gp get() = Money(this * 100)
val Int.pp get() = Money(this * 1000)