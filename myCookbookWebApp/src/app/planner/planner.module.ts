import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlannerDialogComponent } from './planner-dialog/planner-dialog.component';
import { DayPlanComponent } from './planner-dialog/day-plan/day-plan.component';



@NgModule({
  declarations: [PlannerDialogComponent, DayPlanComponent],
  imports: [
    CommonModule
  ],
  exports: [PlannerDialogComponent]
})
export class PlannerModule { }
