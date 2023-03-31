package character

data class Speed(val base: Distance)

class SpeedTrait(val speed: Speed) : Trait {
    override fun CharacterBuilder.apply() {
        speed = this@SpeedTrait.speed
    }
}
