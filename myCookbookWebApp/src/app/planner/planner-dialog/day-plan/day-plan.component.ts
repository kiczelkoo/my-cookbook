import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'mc-app-day-plan',
  templateUrl: './day-plan.component.html',
  styleUrls: ['./day-plan.component.scss']
})
export class DayPlanComponent implements OnInit {

  dayPlan: string

  constructor() { }

  ngOnInit(): void {
  }

}
