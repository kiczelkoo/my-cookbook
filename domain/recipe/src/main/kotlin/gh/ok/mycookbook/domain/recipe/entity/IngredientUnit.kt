package gh.ok.mycookbook.domain.recipe.entity

enum class IngredientUnit(val unitName: String) {
    SPOON("łyżka"),
    TEASPOON("łyżeczka"),
    SLICE("plaster"),
    PIECE("sztuka"),
    PINCH("szczypta"),
    CUP("szklanka"),
    KILOGRAM("kg"),
    GRAM("g"),
    LITER("l"),
    MILLILITER("ml"),
    CLOVE("ząbek");

    companion object {
        fun fromString(unit: String): IngredientUnit {
            return values().first { it.unitName.equals(unit) }
        }
    }
}
