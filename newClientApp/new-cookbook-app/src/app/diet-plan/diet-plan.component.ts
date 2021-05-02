import { Component, Input, OnInit } from '@angular/core';
import { DayPlan } from './day-plan';
import { DietPlanService } from './diet-plan.service';

@Component({
  selector: 'mcb-diet-plan',
  templateUrl: './diet-plan.component.html',
  styleUrls: ['./diet-plan.component.scss']
})
export class DietPlanComponent implements OnInit {

  dailyPlans: DayPlan[] = [];

  selectedDate: Date = new Date();

  constructor(private dietPlanService: DietPlanService) { }

  ngOnInit(): void {
    this.getDietPlans()
  }

  onNewDateSelected(newDate: Date) {
    this.selectedDate = newDate;
    this.getDietPlans();
  }

  private getDietPlans() {
    this.dietPlanService.getDietPlansForWeekStartsFrom(this.selectedDate).subscribe(dailyPlans => {
      this.dailyPlans = dailyPlans;
    })
  }



}
