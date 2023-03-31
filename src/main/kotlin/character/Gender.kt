package character

enum class Gender {
    Male, Female, Any;

    fun <T> choose(male: T, female: T) = when (this) {
        Male -> male
        Female -> female
        Any -> listOf(male, female).random()
    }
}

class GenderTrait(val gender: Gender) : Trait {
    override fun CharacterBuilder.apply() {
        gender = this@GenderTrait.gender
    }
}

fun CharacterBuilder.male() {
    +GenderTrait(Gender.Male)
}

fun CharacterBuilder.female() {
    +GenderTrait(Gender.Female)
}

fun CharacterBuilder.anyGender() {
    +GenderTrait(Gender.Any)
}