package character

import random

class AbilityScores private constructor(val scores: Map<Ability, AbilityScore>) : Map<Ability, AbilityScore> by scores {
    constructor(str: Int, dex: Int, con: Int, int: Int, wis: Int, cha: Int) : this(
        mapOf(
            Ability.Str to AbilityScore(str), Ability.Dex to AbilityScore(dex), Ability.Con to AbilityScore(con),
            Ability.Int to AbilityScore(int), Ability.Wis to AbilityScore(wis), Ability.Cha to AbilityScore(cha),
        )
    )

    constructor() : this(0, 0, 0, 0, 0, 0)

    override operator fun get(key: Ability) =
        scores[key]!!

    override fun toString(): String {
        return scores.entries.sortedBy { it.key }.joinToString { (ability, score) ->
            "${ability.name.uppercase()} ${score.value}"
        }
    }
}

enum class Ability {
    Str, Dex, Con,
    Int, Wis, Cha;
}

data class AbilityScore(var value: Int) {
    val modifier: Int
        get() = value / 2 - 5
}

sealed class AbilityScoreImprovementTrait : Trait {
    class Preset(val improvements: Map<Ability, Int>) : AbilityScoreImprovementTrait() {
        override fun CharacterBuilder.apply() {
            for ((ability, improvement) in improvements) {
                abilityScores[ability].value += improvement
            }
        }
    }
}

sealed class AbilityScoresTrait : Trait {
    sealed class Random : AbilityScoresTrait() {
        object InOrder : Random() {
            override fun CharacterBuilder.apply() {
                for (score in abilityScores.values) {
                    score.value += List(4) { random.nextInt(6) + 1 }.sorted().drop(1).sum()
                }
            }
        }

        class Prioritised(val priorities: List<Ability>) : Random() {
            override fun CharacterBuilder.apply() {
                val abilities = Ability.values().toMutableList()
                val scores = List(6) {
                    List(4) { random.nextInt(6) + 1 }.sorted().drop(1).sum()
                }.sorted().toMutableList()
                for (priority in priorities) {
                    abilityScores[priority].value = scores.removeLast()
                    abilities.remove(priority)
                }
                abilities.shuffle()
                for (ability in abilities) {
                    abilityScores[ability].value = scores.removeLast()
                }
            }
        }
    }
}
