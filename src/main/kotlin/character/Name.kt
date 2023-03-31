package character

import character.CreatureType.Humanoid.Human.Ethnicity.*
import random

@JsModule("./names/dwarven/clan.txt")
@JsNonModule
external val dwarvenClan: String

@JsModule("./names/dwarven/female.txt")
@JsNonModule
external val dwarvenFemale: String

@JsModule("./names/dwarven/male.txt")
@JsNonModule
external val dwarvenMale: String

@JsModule("./names/elven/child.txt")
@JsNonModule
external val elvenChild: String

@JsModule("./names/elven/female.txt")
@JsNonModule
external val elvenFemale: String

@JsModule("./names/elven/male.txt")
@JsNonModule
external val elvenMale: String

@JsModule("./names/elven/surnames.txt")
@JsNonModule
external val elvenSurnames: String

@JsModule("./names/human/calashite/female.txt")
@JsNonModule
external val humanCalashiteFemale: String

@JsModule("./names/human/calashite/male.txt")
@JsNonModule
external val humanCalashiteMale: String

@JsModule("./names/human/calashite/surnames.txt")
@JsNonModule
external val humanCalashiteSurnames: String

@JsModule("./names/human/chondathan/female.txt")
@JsNonModule
external val humanChondathanFemale: String

@JsModule("./names/human/chondathan/male.txt")
@JsNonModule
external val humanChondathanMale: String

@JsModule("./names/human/chondathan/surnames.txt")
@JsNonModule
external val humanChondathanSurnames: String

@JsModule("./names/human/damaran/female.txt")
@JsNonModule
external val humanDamaranFemale: String

@JsModule("./names/human/damaran/male.txt")
@JsNonModule
external val humanDamaranMale: String

@JsModule("./names/human/damaran/surnames.txt")
@JsNonModule
external val humanDamaranSurnames: String

@JsModule("./names/human/illuskan/female.txt")
@JsNonModule
external val humanIlluskanFemale: String

@JsModule("./names/human/illuskan/male.txt")
@JsNonModule
external val humanIlluskanMale: String

@JsModule("./names/human/illuskan/surnames.txt")
@JsNonModule
external val humanIlluskanSurnames: String

@JsModule("./names/human/mulan/female.txt")
@JsNonModule
external val humanMulanFemale: String

@JsModule("./names/human/mulan/male.txt")
@JsNonModule
external val humanMulanMale: String

@JsModule("./names/human/mulan/surnames.txt")
@JsNonModule
external val humanMulanSurnames: String

@JsModule("./names/human/rashemi/female.txt")
@JsNonModule
external val humanRashemiFemale: String

@JsModule("./names/human/rashemi/male.txt")
@JsNonModule
external val humanRashemiMale: String

@JsModule("./names/human/rashemi/surnames.txt")
@JsNonModule
external val humanRashemiSurnames: String

@JsModule("./names/human/shou/female.txt")
@JsNonModule
external val humanShouFemale: String

@JsModule("./names/human/shou/male.txt")
@JsNonModule
external val humanShouMale: String

@JsModule("./names/human/shou/surnames.txt")
@JsNonModule
external val humanShouSurnames: String

@JsModule("./names/human/turami/female.txt")
@JsNonModule
external val humanTuramiFemale: String

@JsModule("./names/human/turami/male.txt")
@JsNonModule
external val humanTuramiMale: String

@JsModule("./names/human/turami/surnames.txt")
@JsNonModule
external val humanTuramiSurnames: String

data class Name(val forename: String, val surname: String) {
    val full get() = "$forename $surname"

    companion object {
        fun random(forenames: String, surnames: String) =
            Name(forenames.lines().random(random), surnames.lines().random(random))
    }
}

class NameTrait : Trait {
    override fun CharacterBuilder.apply() {
        name = when (creatureType) {
            is CreatureType.Humanoid.Human -> {
                val forenames = when ((creatureType as CreatureType.Humanoid.Human).ethnicity) {
                    Calashite -> gender.choose(humanCalashiteMale, humanCalashiteFemale)
                    Chondathan -> gender.choose(humanChondathanMale, humanChondathanFemale)
                    Damaran -> gender.choose(humanDamaranMale, humanDamaranFemale)
                    Illuskan -> gender.choose(humanIlluskanMale, humanIlluskanFemale)
                    Mulan -> gender.choose(humanMulanMale, humanMulanFemale)
                    Rashemi -> gender.choose(humanRashemiMale, humanRashemiFemale)
                    Shou -> gender.choose(humanShouMale, humanShouFemale)
                    Turami -> gender.choose(humanTuramiMale, humanTuramiFemale)
                }
                val surnames = when ((creatureType as CreatureType.Humanoid.Human).ethnicity) {
                    Calashite -> humanCalashiteSurnames
                    Chondathan -> humanChondathanSurnames
                    Damaran -> humanDamaranSurnames
                    Illuskan -> humanIlluskanSurnames
                    Mulan -> humanMulanSurnames
                    Rashemi -> humanRashemiSurnames
                    Shou -> humanShouSurnames
                    Turami -> humanTuramiSurnames
                }
                Name.random(forenames, surnames)
            }

            is CreatureType.Humanoid.Dwarf -> {
                val forenames = gender.choose(dwarvenMale, dwarvenFemale)
                val surnames = dwarvenClan
                Name.random(forenames, surnames)
            }
        }
    }
}