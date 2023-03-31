import character.CreatureType.Humanoid.Dwarf.Subrace.*
import character.CreatureType.Humanoid.Human.Ethnicity.*
import character.*
import character.equipment.EquipmentTrait
import kotlinx.html.div
import kotlinx.html.dom.append
import org.w3c.dom.Node
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = { document.body?.sayHello() }
}

fun Node.sayHello() {
    append {
        div("pages") {
            div("page") {
                div("columnWrapper") {
                    /*div("block monster frame") {
                        h2 {
                            +"Commoner"
                        }
                        p {
                            em {
                                +"Medium humanoid (any race), any alignment"
                            }
                        }
                        hr()
                        dl {
                            dt {
                                strong {
                                    +"Armor Class"
                                }
                            }
                            dd {
                                +"10"
                            }
                            dt {
                                strong {
                                    +"Hit Points"
                                }
                            }
                            dd {
                                +"4 (1d8)"
                            }
                            dt {
                                strong {
                                    +"Speed"
                                }
                            }
                            dd {
                                +"30 ft."
                            }
                        }
                        hr()
                        table {
                            thead {
                                tr {
                                    th {
                                        attributes["align"] = "center"
                                        +"STR"
                                    }
                                    th {
                                        attributes["align"] = "center"
                                        +"DEX"
                                    }
                                    th {
                                        attributes["align"] = "center"
                                        +"CON"
                                    }
                                    th {
                                        attributes["align"] = "center"
                                        +"INT"
                                    }
                                    th {
                                        attributes["align"] = "center"
                                        +"WIS"
                                    }
                                    th {
                                        attributes["align"] = "center"
                                        +"CHA"
                                    }
                                }
                            }
                            tbody {
                                tr {
                                    td {
                                        attributes["align"] = "center"
                                        +"10 (+0)"
                                    }
                                    td {
                                        attributes["align"] = "center"
                                        +"10 (+0)"
                                    }
                                    td {
                                        attributes["align"] = "center"
                                        +"10 (+0)"
                                    }
                                    td {
                                        attributes["align"] = "center"
                                        +"10 (+0)"
                                    }
                                    td {
                                        attributes["align"] = "center"
                                        +"10 (+0)"
                                    }
                                    td {
                                        attributes["align"] = "center"
                                        +"10 (+0)"
                                    }
                                }
                            }
                        }
                        hr()
                        dl {
                            dt {
                                strong {
                                    +"Senses"
                                }
                            }
                            dd {
                                +"passive Perception 10"
                            }
                            dt {
                                strong {
                                    +"Languages"
                                }
                            }
                            dd {
                                +"Any one language (usually Common)"
                            }
                            dt {
                                strong {
                                    +"Challenge"
                                }
                            }
                            dd {
                                +"0 (10 XP)"
                            }
                        }
                        hr()
                        h3 {
                            +"Actions"
                        }
                        p {
                            em {
                                strong {
                                    +"Club."
                                }
                            }
                            +" "
                            em {
                                +"Melee Weapon Attack:"
                            }
                            +" +2 to hit, reach 5 ft., one target. "
                            em {
                                +"Hit:"
                            }
                            +" 2 (1d4) bludgeoning damage."
                        }
                    }*/
                    iterator {
                        while (true) {
                            yield(
                                buildCharacter {
                                    choose {
                                        50 % {
                                            human(choose {
                                                12.5 % { Calashite }
                                                12.5 % { Chondathan }
                                                12.5 % { Damaran }
                                                12.5 % { Illuskan }
                                                12.5 % { Mulan }
                                                12.5 % { Rashemi }
                                                12.5 % { Shou }
                                                12.5 % { Turami }
                                            })
                                        }
                                        50 % {
                                            dwarf(choose {
                                                50 % { Hill }
                                                50 % { Mountain }
                                            })
                                        }
                                    }
                                    choose {
                                        48 % { male() }
                                        48 % { female() }
                                        4 % { anyGender() }
                                    }
                                    +NameTrait()
                                    +AbilityScoresTrait.Random.InOrder
                                    +EquipmentTrait()
                                }
                            )
                        }
                    }.asSequence().find { it.creatureType == CreatureType.Humanoid.Human(Illuskan) }!!
                        .apply { toStatBlock() }
//                    statBlock {
//                        features {
//                            entry(+"Pack Tactics") {
//                                +"""The commoner has advantage on an attack roll against a creature if at least one
//                                    |of the commoner's allies is within 5 feet of the creature and the ally isn't
//                                    |incapacitated.""".trimMargin()
//                            }
//                        }
//                        section(+"Actions") {
//                            entry(+"Club") {
//                                em {
//                                    +"Melee Weapon Attack:"
//                                }
//                                +" +2 to hit, reach 5 ft., one target. "
//                                em {
//                                    +"Hit:"
//                                }
//                                +" 2 (1d4) bludgeoning damage."
//                            }
//                        }
//                    }
                }
            }
        }
    }
}