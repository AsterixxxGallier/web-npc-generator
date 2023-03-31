package character

data class Alignment(val lawfulness: Lawfulness, val goodness: Goodness) {
    enum class Lawfulness {
        Chaotic, Neutral, Lawful
    }

    enum class Goodness {
        Evil, Neutral, Good
    }
}

class AlignmentTrait(val alignment: Alignment) : Trait {
    override fun CharacterBuilder.apply() {
        alignment = this@AlignmentTrait.alignment
    }
}
