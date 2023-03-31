package character

enum class Language {
    Common,
    Dwarvish,
    Elvish,
    Giant,
    Gnomish,
    Goblin,
    Halfling,
    Orc
}

class LanguageTrait(val languages: List<Language>) : Trait {
    override fun CharacterBuilder.apply() {
        languages.addAll(this@LanguageTrait.languages)
    }
}
