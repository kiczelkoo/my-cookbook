import { Component, OnInit, Input } from '@angular/core';
import { DayPlan } from './day-plan-model';

@Component({
  selector: 'mc-app-day-plan',
  templateUrl: './day-plan.component.html',
  styleUrls: ['./day-plan.component.scss']
})
export class DayPlanComponent implements OnInit {

  @Input()
  dayPlan: DayPlan

  constructor() { }

  ngOnInit(): void {
  }

}
