import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlannerDialogComponent } from './planner-dialog/planner-dialog.component';
import { DayPlanComponent } from './planner-dialog/day-plan/day-plan.component';
import { TimelineComponent } from './planner-dialog/timeline/timeline.component';
import { SummaryComponent } from './summary/summary.component';
import { MealComponent } from './planner-dialog/day-plan/meal/meal.component';



@NgModule({
  declarations: [PlannerDialogComponent, DayPlanComponent, TimelineComponent, SummaryComponent, MealComponent],
  imports: [
    CommonModule
  ],
  exports: [PlannerDialogComponent]
})
export class PlannerModule { }
