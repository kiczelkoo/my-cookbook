import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'mcb-day-summary',
  templateUrl: './day-summary.component.html',
  styleUrls: ['./day-summary.component.scss']
})
export class DaySummaryComponent implements OnInit {

  @Input()
  kcal: number;

  @Input()
  carbs: number;

  @Input()
  fats: number;

  @Input()
  proteins: number;

  constructor() { }

  ngOnInit(): void {
  }

}
