import { Dish } from "./dish";
import { Meal } from "./meal";

export type DayPlan = {
    day: Date;
    meals: Meal[];
    kcal: number;
    proteinsPerc: number;
    carbsPerc: number;
    fatsPerc: number;
}

const dishB1main: Dish = {
    name: "Jajecznica z pomidorami i szynką parmeńską",
    serving: 1,
    instruction: [
        "Na patelni rozgrzej tłuszcz, podsmaż pokrojoną w kostkę cebulę i szynkę.",
        "Dodaj pokrojonego w kostkę pomidora i pokrojone w paski suszone pomidory.",
        "Smaż przez około 1 minutę.",
        "Wbij jajka, dodaj sól, pieprz, mieszaj i smaż, aż do ścięcia jaj.",
        "Jajecznicę przełóż i posyp posiekaną natką pietruszki."
    ], ingredients: [
        { name: "Masło klarowane", amount: 1, unit: "tsp" },
        { name: "Cebula", amount: 0.5, unit: "pcs" }, { name: "Szynka parmeńska", amount: 2.5, unit: "slice" },
        { name: "Pomidor", amount: 1, unit: "pcs" }, { name: "Suszone pomidory", amount: 2, unit: "slice" },
        { name: "Jaja kurze małe, rozmiar S", amount: 2, unit: "pcs" }, { name: "Pietruszka liście", amount: 1, unit: "tsp" },
        { name: "Sól morska", amount: 2, unit: "pinch" }, { name: "Pieprz czarny", amount: 2, unit: "pinch" }]
};
const dishB1bev: Dish = {
    name: "Herbata zielona",
    serving: 1,
    ingredients: [{ name: "Herbata zielona liściasta", amount: 1, unit: "cup" }]
}
const breakfest1: Meal = {
    name: "Jajecznica z pomidorami i szynką parmeńską, Herbata zielona", mealType: "BREAKFEST",
    prepTimeInMin: 15, kcal: 380, carbs: 25, fats: 25, proteins: 25, dishes: [dishB1main, dishB1bev]
};

const dishSnck1: Dish = {
    name: "Koktajl kokos-banan-kakao", serving: 1,
    instruction: ["Zblenduj wszystkie składniki.", "Jeśli koktajl będzie zbyt gęsty dodaj wodę - ilość wody dopasuj do pożądanej konsystencji koktajlu."],
    ingredients: [{ name: "Napój kokosowy", amount: 0.5, unit: "cup" }, { name: "Wiórki kokosowe", amount: 1, unit: "tbs" },
    { name: "Banan", amount: 0.5, unit: "pcs" }, { name: "Kakao gorzkie", amount: 1, unit: "tsp" },
    { name: "Płatki owsiane bezglutenowe", amount: 0.75, unit: "tbs" }, { name: "Cynamon mielony", amount: 1, unit: "pinch" }],
}
const snackM1: Meal = {
    name: "Koktajl kokos-banan-kakao", mealType: "SNACK",
    prepTimeInMin: 5, kcal: 207, carbs: 35, fats: 6, proteins: 3, dishes: [dishSnck1]
};

const dishLunch1main: Dish = {
    name: "Kurczak w szynce dojrzewającej z tymiankiem", serving: 1,
    instruction: ["Mięso pokrój w plastry, rozbij, oprósz pieprzem i tymiankiem.", "Na wierzchu ułóż plastry szynki i zwiń całość w roladkę.",
        "Grilluj mięso na patelni z rozgrzanym tłuszczem, aż do zrumienienia.", "Podawaj z mizerią i kaszą."],
    ingredients: [{ name: "Mięso z piersi kurczaka bez skóry", amount: 120, unit: "g" }, { name: "Szynka dojrzewająca", amount: 2, unit: "slice" },
    { name: "Masło klarowane", amount: 0.5, unit: "tsp" }, { name: "Pieprz czarny", amount: 2, unit: "pinch" },
    { name: "Tymianek suszony", amount: 2, unit: "pinch" }]
};
const dishLunch1sideA: Dish = {
    name: "Mizeria na jogurcie", serving: 1, instruction: ["Ogórki pokrój w plastry.", "Wymieszaj jogurt z solą, pieprzem i ogórkami."],
    ingredients: [{ name: "Ogórek gruntowy", amount: 2, unit: "pcs" }, { name: "Jogurt kokosowy", amount: 0.25, unit: "cup" },
    { name: "Sól morska", amount: 1, unit: "pinch" }, { name: "Pieprz czarny", amount: 1, unit: "pinch" }]
};
const dishLunch1sideB: Dish = {
    name: "Kasza jaglana", serving: 1, instruction: ["Przed ugotowaniem przepłucz kaszę 1-2 razy pod bieżącą, zimną wodą.",
        "Kaszę ugotuj według przepisu na opakowaniu."], ingredients: [{ name: "Kasza jaglana", amount: 0.33, unit: "cup" }]
};
const dishLunch1bev: Dish = { name: "Woda mineralna", serving: 1, ingredients: [{ name: "Woda", amount: 1, unit: "cup" }] };
const lunch1: Meal = {
    name: "Kurczak w szynce dojrzewającej z tymiankiem, Mizeria na jogurcie, Kasza jaglana, Woda mineralna",
    mealType: "LUNCH", prepTimeInMin: 30, kcal: 486, carbs: 51, fats: 13, proteins: 41,
    dishes: [dishLunch1main, dishLunch1sideA, dishLunch1sideB, dishLunch1bev]
};

const dishSnackAmain: Dish = {
    name: "Pudding chia z polewą czekoladową", serving: 1, instruction: ["Czekoladę rozpuść w kąpieli wodnej.",
        "Wymieszaj nasiona chia z miodem i napojem roślinnym.", "Schłodź w lodówce przez noc."],
    ingredients: [{ name: "Nasiona chia", amount: 1, unit: "tbs" }, { name: "Napój kokosowy", amount: 0.33, unit: "cup" },
    { name: "Miód pszczeli", amount: 0.66, unit: "tsp" }, { name: "Czekolada gorzka 90%", amount: 12, unit: "g" }]
};
const dishSnackAbev: Dish = { name: "Woda mineralna", serving: 1, ingredients: [{ name: "Woda", amount: 1, unit: "cup" }] };
const snackA1: Meal = {
    name: "Pudding chia z polewą czekoladową, Woda mineralna", mealType: "SNACK", prepTimeInMin: 15, kcal: 153,
    carbs: 15, fats: 7, proteins: 3, dishes: [dishSnackAmain, dishSnackAbev]
};

const dishSupper1main: Dish = {
    name: "Wegetariański makaron ryżowy z sosem sojowym i orzechami", serving: 1,
    instruction: ["Makaron ugotuj zgodnie z przepisem podanym na opakowaniu.", "ieczarki pokrój w plastry, a cebulę w kostkę.",
        "Na patelni rozgrzej tłuszcz i podsmaż cebulę.", "Dodaj sos sojowy, ugotowany makaron, całość mieszaj i smaż przez około 1 minutę.",
        "Przełóż makaron na talerz.", "Całość posyp posiekanym szczypiorkiem i orzechami."],
    ingredients: [{ name: "Makaron ryżowy", amount: 60, unit: "g" }, { name: "Pieczarka uprawna świeża", amount: 4, unit: "pcs" },
    { name: "Cebula", amount: 0.5, unit: "pcs" }, { name: "Masło klarowane", amount: 0.5, unit: "tsp" },
    { name: "Szczypiorek", amount: 1, unit: "tbs" }, { name: "Orzechy nerkowca", amount: 9, unit: "g" },
    { name: "Sos sojowy (tamari, bezglutenowy)", amount: 1, unit: "tbs" }]
};
const dishSupper1bev: Dish = { name: "Woda mineralna", serving: 1, ingredients: [{ name: "Woda", amount: 1, unit: "cup" }] };
const supper1: Meal = {
    name: "Wegetariański makaron ryżowy z sosem sojowym i orzechami, Woda mineralna", mealType: "SUPPER", prepTimeInMin: 30,
    kcal: 337, carbs: 58, fats: 8, proteins: 9, dishes: [dishSupper1main, dishSupper1bev]
};

const day1: DayPlan = {
    day: new Date(2021, 4, 4), kcal: 1562, carbsPerc: 44, fatsPerc: 21, proteinsPerc: 35,
    meals: [breakfest1, snackM1, lunch1, snackA1, supper1]
};
const day2: DayPlan = {
    day: new Date(2021, 4, 5), kcal: 1562, carbsPerc: 44, fatsPerc: 21, proteinsPerc: 35,
    meals: [breakfest1, snackM1, lunch1, snackA1, supper1]
};

const dishB2main: Dish = {
    name: "Kokosowy pudding jaglany z owocami jagodowymi", serving: 1,
    instruction: ["Przed ugotowaniem przepłucz kaszę 1-2 razy pod bieżącą, zimną wodą.",
        "Kaszę ugotuj według przepisu na opakowaniu.",
        "Dodaj mleczko, miód, cynamon i całość zblenduj (jeśli pudding będzie zbyt gęsty, dodaj odrobinę wody i ponownie zblenduj).",
        "Przełóż pudding do miseczki, dodaj na wierzch borówki i pokrojone w plastry truskawki.", "Całość posyp posiekanymi orzechami."],
    ingredients: [{ name: "Kasza jaglana", amount: 0.25, unit: "cup" }, { name: "Mleczko kokosowe 18% tłuszczu", amount: 3, unit: "tbs" },
    { name: "Miód pszczeli", amount: 0.5, unit: "tsp" }, { name: "Maliny", amount: 70, unit: "g" },
    { name: "Truskawki", amount: 70, unit: "g" }, { name: "Orzechy nerkowca", amount: 23, unit: "g" },
    { name: "Cynamon mielony", amount: 1, unit: "pinch" }]
};
const dishB2bev: Dish = { name: "Woda mineralna", serving: 1, ingredients: [{ name: "Woda", amount: 1, unit: "cup" }] };
const breakfest2: Meal = {
    name: "", mealType: "BREAKFEST", prepTimeInMin: 15,
    kcal: 407, carbs: 59, fats: 18, proteins: 16, dishes: [dishB2main, dishB2bev]
};

const dishSnackM2main: Dish = {
    name: "Bezglutenowe placuszki z miodem", serving: 1,
    instruction: ["ajko zmiksuj z mlekiem, solą, proszkiem i mąką.",
        "Na patelni rozgrzej tłuszcz, nakładaj przygotowaną masę łyżką i smaż placuszki z obu stron, aż do zrumienienia.",
        "Placuszki polej miodem."],
    ingredients: [{ name: "Jaja kurze małe, rozmiar S", amount: 1, unit: "pcs" }, { name: "Napój kokosowy", amount: 0.25, unit: "cup" },
    { name: "Proszek do pieczenia, bezglutenowy", amount: 0.5, unit: "tsp" }, { name: "Mąka ryżowa", amount: 1.25, unit: "tbs" },
    { name: "Olej kokosowy extra virgin", amount: 0.5, unit: "tsp" }, { name: "Miód pszczeli", amount: 0.75, unit: "tbs" },
    { name: "Sól morska", amount: 1, unit: "pinch" }]
};
const dishSnackM2bev: Dish = { name: "Woda mineralna", serving: 1, ingredients: [{ name: "Woda", amount: 1, unit: "cup" }] };
const snackM2: Meal = {
    name: "Bezglutenowe placuszki z miodem, Woda mineralna", mealType: "SNACK", prepTimeInMin: 15,
    kcal: 263, carbs: 28, fats: 13, proteins: 7, dishes: [dishSnackM2main, dishSnackM2bev]
};

const dishLunch2main: Dish = {
    name: "Kurczak ze szparagami w sosie z suszonymi pomidorami", serving: 1,
    instruction: ["Mięso przypraw solą, chili, papryką czerwoną słodką i kurkumą.",
        "Szparagi wrzuć do wrzątku i gotuj przez około 7 minut, po czym pokrój na mniejsze kawałki.",
        "Na patelni rozgrzej tłuszcz, podsmaż przeciśnięty przez praskę czosnek i przyprawione mięso.",
        "Dodaj pokrojone w paski suszone pomidory i napój kokosowy.",
        "Całość mieszaj i duś pod przykryciem do momentu, aż mięso będzie miękkie.",
        "Na koniec dodaj szparagi i posiekaną pietruszkę.", "Podawaj danie z kaszą."],
    ingredients: [{ name: "Mięso z piersi kurczaka bez skóry", amount: 180, unit: "g" }, { name: "Szparagi", amount: 4, unit: "pcs" },
    { name: "Olej kokosowy extra virgin", amount: 0.25, unit: "tsp" }, { name: "Czosnek", amount: 1, unit: "pcs" },
    { name: "Suszone pomidory", amount: 3, unit: "slice" }, { name: "Napój kokosowy", amount: 0.25, unit: "cup" },
    { name: "Pietruszka liście", amount: 2, unit: "tsp" }, { name: "Sól morska", amount: 2, unit: "pinch" },
    { name: "Chili w proszku", amount: 2, unit: "pinch" }, { name: "Papryka czerwona słodka", amount: 0.25, unit: "tsp" },
    { name: "Kurkuma mielona", amount: 2, unit: "pinch" }]
};
const dishLunch2side: Dish = {
    name: "Kasza gryczana", serving: 1,
    instruction: ["Przed ugotowaniem przepłucz kaszę 1-2 razy pod bieżącą, zimną wodą.",
        "Kaszę ugotuj według przepisu na opakowaniu."],
    ingredients: [{ name: "Kasza gryczana niepalona", amount: 0.25, unit: "cup" }]
};
const dishLunch2bev: Dish = { name: "Woda mineralna", serving: 1, ingredients: [{ name: "Woda", amount: 1, unit: "cup" }] };
const lunch2: Meal = {
    name: "Kurczak ze szparagami w sosie z suszonymi pomidorami, Kasza gryczana, Woda mineralna", mealType: "LUNCH", prepTimeInMin: 45,
    kcal: 479, carbs: 54, fats: 8, proteins: 50, dishes: [dishLunch2main, dishLunch2side, dishLunch2bev]
};

const dishSA2main: Dish = {
    name: "Jogurt kokosowy z dodatkami", serving: 1,
    instruction: ["Wymieszaj jogurt z pozostałymi składnikami."],
    ingredients: [{ name: "Jogurt kokosowy", amount: 0.33, unit: "cup" }, { name: "Słonecznik nasiona", amount: 0.75, unit: "tbs" },
    { name: "Miód pszczeli", amount: 0.25, unit: "tsp" }, { name: "Cynamon mielony", amount: 1, unit: "pinch" }]
};
const dishSA2bev: Dish = { name: "Woda mineralna", serving: 1, ingredients: [{ name: "Woda", amount: 1, unit: "cup" }] };
const snackA2: Meal = {
    name: "Jogurt kokosowy z dodatkami, Woda mineralna", mealType: "SNACK", prepTimeInMin: 5,
    kcal: 123, carbs: 12, fats: 7, proteins: 2, dishes: [dishSA2main, dishSA2bev]
};

const dishS2main: Dish = {
    name: "Naleśniki z mąki ryżowej - 1 porcja z 2", serving: 1,
    instruction: ["Przepis na 2 porcje.", "Do miski wsyp mąkę ryżową i ziemniaczaną.",
        "Dodaj jajka i mleko roślinne i sól.", "Składniki zblenduj na gładkie ciasto.",
        "Smaż masę na naleśniki na patelni beztłuszczowej."],
    ingredients: [{ name: "Mąka ryżowa", amount: 2, unit: "tbs" }, { name: "Mąka ziemniaczana", amount: 1, unit: "tsp" },
    { name: "Jaja kurze małe, rozmiar S", amount: 1, unit: "pcs" }, { name: "Napój ryżowy", amount: 0.5, unit: "cup" },
    { name: "Sól morska", amount: 1, unit: "pinch" }]
};
const dishS2filling: Dish = {
    name: "Farsz szpinakowo-pomidorowy", serving: 1,
    instruction: ["Na maśle podsmaż czosnek wraz z suszonymi pomidorami.",
        "Dodaj szpinak i duś przez chwilę."],
    ingredients: [{ name: "Szpinak", amount: 50, unit: "g" }, { name: "Masło klarowane", amount: 1, unit: "tsp" },
    { name: "Suszone pomidory", amount: 6, unit: "slice" }, { name: "Czosnek", amount: 0.5, unit: "pcs" }]
};
const dishS2bev: Dish = { name: "Herbata", serving: 1, ingredients: [{ name: "Herbata zielona liściasta", amount: 1, unit: "cup" }] };
const supper2: Meal = {
    name: "Naleśniki z mąki ryżowej, Farsz szpinakowo-pomidorowy, Herbata", mealType: "SUPPER", prepTimeInMin: 15,
    kcal: 317, carbs: 46, fats: 11, proteins: 10, dishes: [dishS2main, dishS2filling, dishS2bev]
};

const day3: DayPlan = {
    day: new Date(2021, 4, 6), kcal: 1589, carbsPerc: 48, fatsPerc: 21, proteinsPerc: 31,
    meals: [breakfest2, snackM2, lunch2, snackA2, supper2]
};
const day4: DayPlan = {
    day: new Date(2021, 4, 7), kcal: 1589, carbsPerc: 48, fatsPerc: 21, proteinsPerc: 31,
    meals: [breakfest2, snackM2, lunch2, snackA2, supper2]
};

export const mockPlan: DayPlan[] = [day1, day2, day3, day4];