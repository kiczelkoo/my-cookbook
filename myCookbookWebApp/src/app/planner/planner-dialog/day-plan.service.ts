import { Injectable } from '@angular/core';
import { DayPlan } from './day-plan-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DayPlanService {

  constructor() { }

  getDayPlansForDates(): Observable<DayPlan[]> {
    return Observable.create()
  }
}

const dayPlan1: DayPlan = {
  kcal: 1500, carbs: 100 
}