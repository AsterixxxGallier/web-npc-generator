package character

import character.Ability.Dex
import character.Alignment.*
import character.equipment.Armor
import character.equipment.EquipmentTrait
import kotlinx.html.TagConsumer
import statBlock
import kotlin.math.min

data class Character(
    val creatureType: CreatureType,
    val gender: Gender,
    // TODO: apparent sex
    val name: Name,
    val size: Size,
    val alignment: Alignment?,
    val age: Age,
    val speed: Speed,
    val languages: List<Language>,
    val abilityScores: AbilityScores,
    val armor: Armor?,
    // TODO: change this to an actual item that like might be magical and stuff
    val shield: Boolean,
) {
    fun <T, C : TagConsumer<T>> C.toStatBlock() {
        statBlock {
            title(+name.full)
            subtitle(+"${size.name} ${
                when (creatureType) {
                    is CreatureType.Humanoid.Dwarf -> "humanoid (${creatureType.subrace.name.lowercase()} dwarf)"
                    is CreatureType.Humanoid.Human -> "humanoid (${creatureType.ethnicity.name} human)"
                }
            }, ${
                if (alignment == null) "unaligned"
                else if (alignment.lawfulness == Lawfulness.Neutral && alignment.goodness == Goodness.Neutral) "neutral"
                else "${alignment.lawfulness.name.lowercase()} ${alignment.goodness.name.lowercase()}"
            }")
            attribute(+"Armor Class", +"$armorClass${
                armor?.let { " (${it.name.lowercase()}${
                    if (shield) ", shield"
                    else ""
                })" } ?: ""}")
            attribute(+"Speed", +"${speed.base}")
            abilityScores.entries.sortedBy { it.key }.forEach { (ability, score) ->
                val modifier = score.modifier
                score(+ability.name.uppercase(), +"${score.value.toString().padStart(2)} (${
                    if (modifier < 0) "$modifier"
                    else "+$modifier"
                })")
            }
            detail(+"Languages", +languages.joinToString { it.name })
//            features {
//                entry(+"Pack Tactics") {
//                    +"""The commoner has advantage on an attack roll against a creature if at least one
//                                    |of the commoner's allies is within 5 feet of the creature and the ally isn't
//                                    |incapacitated.""".trimMargin()
//                }
//            }
//            section(+"Actions") {
//                entry(+"Club") {
//                    em {
//                        +"Melee Weapon Attack:"
//                    }
//                    +" +2 to hit, reach 5 ft., one target. "
//                    em {
//                        +"Hit:"
//                    }
//                    +" 2 (1d4) bludgeoning damage."
//                }
//            }
        }
    }

    val armorClass
        get() = when (armor?.type) {
            Armor.ArmorType.Light -> armor.baseAC + abilityScores[Dex].modifier
            Armor.ArmorType.Medium -> armor.baseAC + min(abilityScores[Dex].modifier, 2)
            Armor.ArmorType.Heavy -> armor.baseAC
            null -> 10 + abilityScores[Dex].modifier
        } + if (shield) 2 else 0
}

class CharacterBuilder {
    lateinit var creatureType: CreatureType
    lateinit var gender: Gender
    lateinit var name: Name
    lateinit var size: Size
    var alignment: Alignment? = null
    lateinit var age: Age
    lateinit var speed: Speed
    val languages = mutableListOf<Language>()
    val abilityScores = AbilityScores()
    var armor: Armor? = null
    var shield: Boolean = false

    val traits = mutableListOf<Trait>()

    fun build(): Character {
        applyTraits<CreatureTypeTrait>()
        applyTraits<SizeTrait>()
        applyTraits<GenderTrait>()
        applyTraits<NameTrait>()
        applyTraits<AlignmentTrait>()
        applyTraits<AgeTrait>()
        applyTraits<SpeedTrait>()
        applyTraits<LanguageTrait>()
        applyTraits<AbilityScoreImprovementTrait.Preset>()
        applyTraits<AbilityScoresTrait>()
        applyTraits<EquipmentTrait>()
        return Character(creatureType, gender, name, size, alignment, age, speed, languages, abilityScores, armor, shield)
    }

    inline fun <reified T : Trait> applyTraits() {
        for (it in traits.filterIsInstance<T>()) {
            with(it) { apply() }
        }
    }

    operator fun Trait.unaryPlus() {
        traits.add(this)
    }
}

fun buildCharacter(block: CharacterBuilder.() -> Unit) =
    CharacterBuilder().apply(block).build()

interface Trait {
    fun CharacterBuilder.apply()
}