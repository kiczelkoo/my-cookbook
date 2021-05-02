import { Dish } from "./dish";

export type Meal = {
    name: string;
    mealType: "BREAKFEST" | "LUNCH" | "DINNER" | "SUPPER" | "SNACK";
    kcal: number;
    proteins: number;
    carbs: number;
    fats: number;
    prepTimeInMin: number;
    dishes: Dish[];
}

// const meal: Meal = {name: "muffinki", mealType: "SNACK", };
