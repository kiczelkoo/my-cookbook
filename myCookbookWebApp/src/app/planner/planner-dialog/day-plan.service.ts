import { Injectable } from '@angular/core';
import { DayPlan, Recipe } from './day-plan-model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DayPlanService {

  constructor() { }

  getDayPlansForDates(fromDate: string, toDate: string): Observable<DayPlan[]> {
    console.log('send request for day plans for dates: ', fromDate, toDate)
    return of([dayPlan1])
  }
}

const breakfast: Recipe = {
  category: 'BREAKFAST',
  name: 'Omlet z marchewką',
  summary: {kcal: 294,
  carbs: { amount: 10, unit: 'g' },
  prots: { amount: 24, unit: 'g' },
  fats: { amount: 18, unit: 'g' }},
  time: '<15 min.',
  ingredients: [{ product: 'Jaja kurze małe, rozmiar S', amount: { amount: 3, unit: 'szt' }, stdAmount: { amount: 150, unit: 'g' } },
  { product: 'Białko jaja kurzego', amount: { amount: 1, unit: 'szt' }, stdAmount: { amount: 35, unit: 'g' } },
  { product: 'Marchew', amount: { amount: 2, unit: 'szt' }, stdAmount: { amount: 90, unit: 'g' } },
  { product: 'Masło klarowane', amount: { amount: 0.5, unit: 'łyżeczki' }, stdAmount: { amount: 4, unit: 'g' } },
  { product: 'Kardamon, mielony', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Cynamon mielony', amount: { amount: 0.2, unit: 'łyżeczki' } },
  { product: 'Imbir mielony', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Pieprz czarny', amount: { amount: 2, unit: 'szczypty' } },
  { product: 'Sól morska', amount: { amount: 3, unit: 'szczypty' } }],
  description: [{
    name: 'Omlet z marchewką', instructions: ['Rozpuść tłuszcz na patelni, wlej masę i przykryj pokrywką.',
      'Gdy masa się zetnie, omlet przerzuć na drugą stronę.',
      'Omlet złóż na pół i wyłóż na talerz.']
  },
  { name: 'Herbata zielona liściasta', instructions: [] }]
}

const snackOne: Recipe = {
  category: 'SNACK',
  name: 'Koktajl migdałowo-kakaowy',
  summary: {kcal: 244,
  prots: { amount: 5, unit: 'g' },
  fats: { amount: 6, unit: 'g' },
  carbs: { amount: 42, unit: 'g' }},
  time: '<15 min.',
  ingredients: [{ product: 'Banan', amount: { amount: 0.5, unit: 'szt' }, stdAmount: { amount: 100, unit: 'g' } },
  { product: 'Banan', amount: { amount: 0.5, unit: 'szt' }, stdAmount: { amount: 100, unit: 'g' } },
  { product: 'Mleko migdałowe', amount: { amount: 0.5, unit: 'szklanki' }, stdAmount: { amount: 120, unit: 'ml' } },
  { product: 'Daktyle suszone niesiarkowane', amount: { amount: 3, unit: 'szt' }, stdAmount: { amount: 15, unit: 'g' } },
  { product: 'Kakao gorzkie', amount: { amount: 2, unit: 'łyżeczki' }, stdAmount: { amount: 10, unit: 'g' } },
  { product: 'Nasiona chia', amount: { amount: 1, unit: 'łyżeczka' }, stdAmount: { amount: 5, unit: 'g' } },
  { product: 'Cynamon mielony', amount: { amount: 0.2, unit: 'łyżeczki' } },
  { product: 'Ekstrakt z wanilii', amount: { amount: 1, unit: 'łyżeczka' }, stdAmount: { amount: 4, unit: 'g' } },
  { product: 'Sól morska', amount: { amount: 1, unit: 'szczypta' } }],
  description: [{
    name: 'Koktajl migdałowo - kakaowy', instructions: ['Daktyle zalej wrzątkiem i odstaw na chwilę(najlepiej 10 - 20 minut).',
      'Zblenduj wszystkie składniki.']
  }]
}

const lunch: Recipe = {
  category: 'LUNCH',
  time: '<45 min.',
  name: 'Grillowany łosoś w sosie z cytryny',
  summary: {kcal: 446,
  prots: { amount: 36, unit: 'g' },
  fats: { amount: 28, unit: 'g' },
  carbs: { amount: 13, unit: 'g' }},
  ingredients: [
    { product: 'Łosoś świeży', amount: { amount: 175, unit: 'g' } },
    { product: 'Sok pomarańczowy, świeżo wyciśnięty', amount: { amount: 3, unit: 'łyżki' }, stdAmount: { amount: 18, unit: 'ml' } },
    { product: 'Sok wyciśnięty z cytryny', amount: { amount: 2, unit: ' łyżki' }, stdAmount: { amount: 12, unit: 'g' } },
    { product: 'Czosnek', amount: { amount: 1, unit: 'ząbek' }, stdAmount: { amount: 5, unit: 'g' } },
    { product: 'Masło klarowane', amount: { amount: 0.25, unit: 'łyżeczki' }, stdAmount: { amount: 2, unit: 'g' } },
    { product: 'Sos sojowy(tamari, bezglutenowy)', amount: { amount: 1, unit: 'łyżeczka' }, stdAmount: { amount: 5, unit: 'ml' } },
    { product: 'Imbir świeży', amount: { amount: 1, unit: 'plaster' }, stdAmount: { amount: 10, unit: 'g' } },
    { product: 'Pieprz cayenne', amount: { amount: 2, unit: 'szczypty' } },
    { product: 'Sól himalajska', amount: { amount: 2, unit: 'szczypty' } },
    { product: 'Roszponka', amount: { amount: 0.5, unit: 'garści' }, stdAmount: { amount: 15, unit: 'g' } },
    { product: 'Oliwa z oliwek extra virgin', amount: { amount: 0.5, unit: 'łyżeczki' }, stdAmount: { amount: 3, unit: 'g' } },
    { product: 'Miód pszczeli', amount: { amount: 0.5, unit: 'łyżeczki' }, stdAmount: { amount: 6, unit: 'g' } },
    { product: 'Sok wyciśnięty z cytryny', amount: { amount: 2, unit: 'łyżki' }, stdAmount: { amount: 12, unit: 'g' } },
    { product: 'Musztarda dijon', amount: { amount: 0.5, unit: 'łyżeczki' }, stdAmount: { amount: 5, unit: 'g' } },
    { product: 'Sól morska', amount: { amount: 2, unit: 'szczypty' } }],
  description: [{
    name: 'Grillowany łosoś w sosie z cytryny',
    instructions: ['Umyj łososia i osusz.', 'Przygotuj marynatę: wymieszaj w miseczce sok z cytryny, pomarańczy, tarty imbir, przeciśnięty przez praskę czosnek, pieprz i sól.',
      'Rybę zanurz w marynacie.', 'Rozgrzej piekarnik do 180 °C.', 'Rybę obłożoną wiórkami masła ułóż na blaszce, wstaw do piekarnika i piecz przez 15 minut.',
      'Po wyciągnięciu polej sosem sojowym.']
  },
  { name: 'Zielone pesto', instructions: ['Zblenduj wszystkie składniki na gładki sos i polej upieczonego łososia.'] },
  { name: 'Woda mineralna', instructions: [] }]
}

const snackTwo: Recipe = {
  category: 'SNACK',
  name: 'Krem z buraków z gruszką',
  summary: {kcal: 186,
  prots: { amount: 6, unit: 'g' },
  fats: { amount: 1, unit: 'g' },
  carbs: { amount: 49, unit: 'g' }},
  ingredients: [{ product: 'Burak', amount: { amount: 1.5, unit: 'szt' }, stdAmount: { amount: 158, unit: 'g' } },
  { product: 'Marchew', amount: { amount: 1, unit: 'szt' }, stdAmount: { amount: 45, unit: 'g' } },
  { product: 'Pietruszka korzeń', amount: { amount: 1, unit: 'szt' }, stdAmount: { amount: 80, unit: 'g' } },
  { product: 'Gruszka', amount: { amount: 1, unit: 'szt' }, stdAmount: { amount: 130, unit: 'g' } },
  { product: 'Czosnek', amount: { amount: 1, unit: 'ząbek' }, stdAmount: { amount: 5, unit: 'g' } },
  { product: 'Sok wyciśnięty z cytryny', amount: { amount: 1, unit: 'łyżka' }, stdAmount: { amount: 6, unit: 'g' } },
  { product: 'Liść laurowy', amount: { amount: 1, unit: 'liść' }, stdAmount: { amount: 1, unit: 'g' } },
  { product: 'Majeranek suszony', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Tymianek suszony', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Kminek', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Kurkuma mielona', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Imbir mielony', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Pieprz czarny', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Sól morska', amount: { amount: 1, unit: 'szczypta' } }
  ],
  description: [{
    name: 'Krem z buraków z gruszką', instructions: ['Marchewki, pietruszkę umyj i obierz.',
      'W garnku zagotuj wodę dodaj warzywa oraz liść laurowy, kminek, tymianek i sól.',
      'W osobnym garnku ugotuj buraki w mundurkach.',
      'Gdy buraki będą miękkie, pokrój w kostkę i dodaj do wywaru z warzywami.',
      'Dodaj pokrojone gruszki, przeciśnięty przez praskę czosnek majeranek, kurkumę, imbir oraz pieprz.',
      'Warzywa gotuj do miękkości.',
      'Dodaj sok z cytryny i zblenduj na krem.']
  }]
}

const dinner: Recipe = {
  category: 'DINNER',
  time: '<15 min.',
  name: 'Pasta brokułowa',
  summary: {kcal: 265,
  prots: { amount: 10, unit: 'g' },
  fats: { amount: 5, unit: 'g' },
  carbs: { amount: 53, unit: 'g' }},
  ingredients: [{ product: 'Brokuły', amount: { amount: 0.3, unit: 'szt' }, stdAmount: { amount: 167, unit: 'g' } },
  { product: 'Czosnek', amount: { amount: 1, unit: 'ząbek' }, stdAmount: { amount: 5, unit: 'g' } },
  { product: 'Oliwa z oliwek extra virgin', amount: { amount: 0.75, unit: 'łyżeczki' }, stdAmount: { amount: 4, unit: 'g' } },
  { product: 'Pieprz czarny', amount: { amount: 1, unit: 'szczypta' } },
  { product: 'Sól morska', amount: { amount: 1, unit: 'szczypta' } }
  ],
  description: [{ name: 'Pasta brokułowa', instructions: ['Brokuły ugotuj do miękkości', 'Zblenduj wszystkie składniki.'] },
  { name: 'Chleb żytni', instructions: [] }, { name: 'Herbata ziołowa', instructions: [] }]
}

const dayPlan1: DayPlan = {
  summary: {kcal: 1436,
  carbs: { amount: 44, unit: '%' },
  prots: { amount: 21, unit: '%' },
  fats: { amount: 35, unit: '%' }},
  day: '2016-08-03', meals: [breakfast, snackOne, lunch, snackTwo, dinner]
}


