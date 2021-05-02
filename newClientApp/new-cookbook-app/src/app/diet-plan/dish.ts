import { Ingredient } from "./ingredient";

export type Dish = {
    name: string;
    ingredients: Ingredient[];
    instruction?: string[];
    serving: number;
}