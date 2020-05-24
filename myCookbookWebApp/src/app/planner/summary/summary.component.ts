import { Component, OnInit, Input } from '@angular/core';
import { Summary } from '../planner-dialog/day-plan-model';

@Component({
  selector: 'mc-app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.scss']
})
export class SummaryComponent implements OnInit {

  @Input()
  summary: Summary

  // TODO calculate based on recipes' summaries

  constructor() { }

  ngOnInit(): void {
  }

}
