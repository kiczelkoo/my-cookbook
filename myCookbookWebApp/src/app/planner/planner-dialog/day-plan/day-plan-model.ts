// TODO in future some types could be moved to shared module
export type DayPlan = {
    summary: Summary;    
    meals: Recipe[];
    day: string;
};

export type Recipe = {
    category: string;
    name: string;
    summary: Summary;
    time?: string
    description: Description[];
    ingredients: Ingredient[];
}

export type Summary = {
    kcal: number;
    prots: Amount;
    carbs: Amount;
    fats: Amount;
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

