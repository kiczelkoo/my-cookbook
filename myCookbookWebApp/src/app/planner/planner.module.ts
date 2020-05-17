import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlannerDialogComponent } from './planner-dialog/planner-dialog.component';



@NgModule({
  declarations: [PlannerDialogComponent],
  imports: [
    CommonModule
  ],
  exports: [PlannerDialogComponent]
})
export class PlannerModule { }
