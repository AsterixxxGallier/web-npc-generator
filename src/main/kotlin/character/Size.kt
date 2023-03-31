package character

enum class Size {
    Tiny, Small, Medium, Large, Huge, Gargantuan
}

class SizeTrait(val size: Size) : Trait {
    override fun CharacterBuilder.apply() {
        size = this@SizeTrait.size
    }
}
