package character

import character.Age.Stage.*
import character.Alignment.*
import character.CreatureType.Humanoid.*
import character.CreatureType.Humanoid.Dwarf.Subrace.*
import random

sealed class CreatureType {
    sealed class Humanoid : CreatureType() {
        data class Human(val ethnicity: Ethnicity) : Humanoid() {
            enum class Ethnicity {
                Calashite, Chondathan, Damaran, Illuskan, Mulan, Rashemi, Shou, Turami
            }
        }

        data class Dwarf(val subrace: Subrace) : Humanoid() {
            enum class Subrace {
                Hill, Mountain
            }
        }
    }
}

class CreatureTypeTrait(val creatureType: CreatureType) : Trait {
    override fun CharacterBuilder.apply() {
        creatureType = this@CreatureTypeTrait.creatureType
    }
}

fun CharacterBuilder.human(ethnicity: Human.Ethnicity) {
    +CreatureTypeTrait(Human(ethnicity))
    +SizeTrait(Size.Medium)
    +SpeedTrait(Speed(30.ft))
    +AbilityScoreImprovementTrait.Preset(Ability.values().associateWith { 1 })
    +AlignmentTrait(Alignment(
        choose {
            33 % { Lawfulness.Chaotic }
            33 % { Lawfulness.Neutral }
            33 % { Lawfulness.Lawful }
        },
        choose {
            33 % { Goodness.Evil }
            33 % { Goodness.Neutral }
            33 % { Goodness.Good }
        }
    ))
    +LanguageTrait(listOf(
        Language.Common,
        choose {
            10 % { Language.Dwarvish }
            10 % { Language.Elvish }
            10 % { Language.Giant }
            10 % { Language.Gnomish }
            10 % { Language.Goblin }
            10 % { Language.Halfling }
            10 % { Language.Orc }
        }
    ))
    val age = random.nextInt(120)
    +AgeTrait(
        Age(
            age, when (age) {
                in 0..2 -> Infant
                in 3..5 -> SmallChild
                in 6..13 -> Child
                in 14..20 -> Adolescent
                in 21..40 -> Mature
                in 41..60 -> MiddleAged
                in 61..90 -> Old
                else -> Venerable
            }
        )
    )
}

fun CharacterBuilder.dwarf(subrace: Dwarf.Subrace) {
    +CreatureTypeTrait(Dwarf(subrace))
    +SizeTrait(Size.Medium)
    +SpeedTrait(Speed(25.ft))
    +AbilityScoreImprovementTrait.Preset(mapOf(Ability.Con to 2))
    +AlignmentTrait(Alignment(
        choose {
            10 % { Lawfulness.Chaotic }
            20 % { Lawfulness.Neutral }
            70 % { Lawfulness.Lawful }
        },
        choose {
            10 % { Goodness.Evil }
            30 % { Goodness.Neutral }
            60 % { Goodness.Good }
        }
    ))
    +LanguageTrait(listOf(Language.Common, Language.Dwarvish))
    when (subrace) {
        Hill -> {
            +AbilityScoreImprovementTrait.Preset(mapOf(Ability.Wis to 1))
            val age = random.nextInt(450)
            +AgeTrait(
                Age(
                    age, when (age) {
                        in 0..3 -> Infant
                        in 4..8 -> SmallChild
                        in 9..16 -> Child
                        in 17..30 -> Adolescent
                        in 31..120 -> Mature
                        in 121..250 -> MiddleAged
                        in 251..350 -> Old
                        else -> Venerable
                    }
                )
            )
        }

        Mountain -> {
            +AbilityScoreImprovementTrait.Preset(mapOf(Ability.Str to 2))
            val age = random.nextInt(525)
            +AgeTrait(
                Age(
                    age, when (age) {
                        in 0..3 -> Infant
                        in 4..8 -> SmallChild
                        in 9..16 -> Child
                        in 17..35 -> Adolescent
                        in 36..150 -> Mature
                        in 151..275 -> MiddleAged
                        in 276..400 -> Old
                        else -> Venerable
                    }
                )
            )
        }
    }
}