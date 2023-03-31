package character

data class Distance(val feet: Int) {
    override fun toString() =
        "$feet ft."
}

val Int.ft get() = Distance(this)
