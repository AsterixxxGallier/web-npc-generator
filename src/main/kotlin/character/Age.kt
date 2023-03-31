package character

data class Age(val years: Int, val stage: Stage) {
    enum class Stage {
        Infant,
        SmallChild,
        Child,
        Adolescent,
        Mature,
        MiddleAged,
        Old,
        Venerable,
    }
}

class AgeTrait(val age: Age) : Trait {
    override fun CharacterBuilder.apply() {
        age = this@AgeTrait.age
    }
}
