import { Component, OnInit } from '@angular/core';
import { DayPlanService } from './day-plan.service';
import { DayPlan } from './day-plan-model';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators'


@Component({
  selector: 'mc-app-planner-dialog',
  templateUrl: './planner-dialog.component.html',
  styleUrls: ['./planner-dialog.component.scss']
})
export class PlannerDialogComponent implements OnInit {

  private destroySubject = new Subject();

  dayPlans: DayPlan[]

  currentDayPlan: DayPlan;

  constructor(private dayplanService: DayPlanService) { }

  ngOnInit(): void {
    this.getDayPlans('2020-05-18', '202-05-24');
  }

  getDayPlans(fromDate: string, toDate: string) {
    this.dayplanService.getDayPlansForDates()
    .pipe(takeUntil(this.destroySubject))
    .subscribe(dayPlans => {
      console.log('found some day plans', dayPlans)
      this.dayPlans = dayPlans;
    })
  }

  onShowDayPlanClick() {
    if (this.dayPlans && this.dayPlans.length > 0) {
      this.currentDayPlan = this.dayPlans[0]
    }
  }



}
