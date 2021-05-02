import { Component, Input, OnInit } from '@angular/core';
import { DayPlan } from './day-plan';
import { DietPlanService } from './diet-plan.service';

@Component({
  selector: 'app-diet-plan',
  templateUrl: './diet-plan.component.html',
  styleUrls: ['./diet-plan.component.scss']
})
export class DietPlanComponent implements OnInit {

  dailyPlans: DayPlan[] = [];

  selectedDate: Date = new Date();

  constructor(private dietPlanService: DietPlanService) { }

  ngOnInit(): void {
    this.dietPlanService.getDietPlansForWeekStartsFrom(this.selectedDate).subscribe(dailyPlans => {
      this.dailyPlans = dailyPlans;
    })
  }

  

}
