import { Component, OnInit } from '@angular/core';
import { DayPlanService } from './day-plan.service';
import { DayPlan } from './day-plan-model';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators'
import { toLongDateFormat, calculateTimeRange } from '../date-util';


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
  }

  onNewDateSelected(selectedDate: Date) {
    const timeRange: [Date, Date] = calculateTimeRange(selectedDate, 4);
    const fromDate = toLongDateFormat(timeRange[0]);
    const toDate = toLongDateFormat(timeRange[1]);
    this.dayplanService.getDayPlansForDates(fromDate, toDate)
      .pipe(takeUntil(this.destroySubject))
      .subscribe(dayPlans => {
        this.dayPlans = dayPlans;
        this.currentDayPlan = this.getCurrentDayPlan(selectedDate);
      });
  }

  private getCurrentDayPlan(selectedDate: Date): DayPlan {
    return this.dayPlans[0];
  }
}
