import { Component, Input, OnInit } from '@angular/core';
import { DayPlan } from './day-plan';
import { DietPlanService } from './diet-plan.service';

@Component({
  selector: 'mcb-diet-plan',
  templateUrl: './diet-plan.component.html',
  styleUrls: ['./diet-plan.component.scss']
})
export class DietPlanComponent implements OnInit {

  selectedDate: Date = new Date();
  selectedPlan: DayPlan;
  
  private dailyPlans: DayPlan[] = [];
  
  constructor(private dietPlanService: DietPlanService) { }

  ngOnInit(): void {
    this.getDietPlan()
  }

  onNewDateSelected(newDate: Date) {
    this.selectedDate = newDate;
    this.getDietPlan();
  }

  private getDietPlan() {
    this.dietPlanService.getDietPlansForDay(this.selectedDate).subscribe(dailyPlans => {
      this.dailyPlans = dailyPlans;
      this.selectedPlan = this.dailyPlans[0]; // TODO selection logic must be implemented
    })
  }



}
