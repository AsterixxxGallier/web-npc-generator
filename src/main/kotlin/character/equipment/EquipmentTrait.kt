package character.equipment

import character.CharacterBuilder
import character.Trait
import random

class EquipmentTrait : Trait {
    override fun CharacterBuilder.apply() {
        armor = Armor.values().random(random)
        shield = random.nextBoolean()
    }
}