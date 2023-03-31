package character.equipment

import character.equipment.Armor.ArmorType.*

@Suppress("EnumEntryName")
enum class Armor(
    val cost: Money,
    val weight: Weight,
    val baseAC: Int,
    val type: ArmorType,
    val strRequirement: Int = 0,
    val stealthDisadvantage: Boolean = false,
) {
    Padded(5.gp, 8.lb, 11, Light, stealthDisadvantage = true),
    Leather(10.gp, 10.lb, 11, Light),
    @JsName("StuddedLeather")
    `Studded leather`(45.gp, 13.lb, 12, Light),

    Hide(10.gp, 12.lb, 12, Medium),
    @JsName("ChainShirt")
    `Chain shirt`(50.gp, 20.lb, 13, Medium),
    @JsName("ScaleMail")
    `Scale mail`(50.gp, 45.lb, 14, Medium, stealthDisadvantage = true),
    Breastplate(400.gp, 20.lb, 14, Medium),
    @JsName("HalfPlate")
    `Half plate`(750.gp, 40.lb, 15, Medium, stealthDisadvantage = true),

    @JsName("RingMail")
    `Ring mail`(30.gp, 40.lb, 14, Heavy, stealthDisadvantage = true),
    @JsName("ChainMail")
    `Chain mail`(75.gp, 55.lb, 16, Heavy, 13, true),
    Splint(200.gp, 60.lb, 17, Heavy, 15, true),
    Plate(1500.gp, 65.lb, 18, Heavy, 15, true),

//    Shield(10.gp, 6.lb, 2, ArmorType.Shield),
    ;

    enum class ArmorType {
        Light,
        Medium,
        Heavy
    }
}