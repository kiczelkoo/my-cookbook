import { Component, Input, OnInit } from '@angular/core';
import { DayPlan } from '../day-plan';

@Component({
  selector: 'mcb-day-plan',
  templateUrl: './day-plan.component.html',
  styleUrls: ['./day-plan.component.scss']
})
export class DayPlanComponent implements OnInit {

  @Input()
  plan: DayPlan;

  constructor() { }

  ngOnInit(): void {
  }

}
