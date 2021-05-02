import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { DayPlan, mockPlan } from './day-plan';
import { Dish } from './dish';
import { Meal } from './meal';

@Injectable({
  providedIn: 'root'
})
export class DietPlanService {

  constructor() { }

  getDietPlansForWeekStartsFrom(date: Date): Observable<DayPlan[]> {
    return of(mockPlan);
  }

}
