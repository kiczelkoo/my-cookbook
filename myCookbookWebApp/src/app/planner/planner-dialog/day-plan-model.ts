export type DayPlan = {
    kcal: number;
    day: string;
    prot: Amount;
    carbs: Amount;
    fats: Amount;    
    meals: Recipe[];
};

// TODO in future could be moved to shared module
export type Recipe = {
    category: string;
    name: string;
    kcal: number;
    prot: Amount;
    carbs: Amount;
    fats: Amount;
    time?: string
    description: Description[];
    ingredients: Ingredient[];
}

export type Description = {
    name: string;
    instructions: string[]
}

export type Ingredient = {
    product: string;
    amount: Amount;
    stdAmount?: Amount;
}

export type Amount = {
    amount: number;
    unit: string;
}
