export type Ingredient = {
    name: string;
    amount: number;
    unit: "g" | "ml" | "tbs" | "cup" | "tsp" | "pinch" | "pcs" | "slice"
    category?: string; // todo find categories
}