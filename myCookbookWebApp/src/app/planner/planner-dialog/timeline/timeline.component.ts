import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';

@Component({
  selector: 'mc-app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {

  selectedDate: string;

  displaydDates: string[]

  constructor() { }

  ngOnInit(): void {
    const now = new Date();
    this.selectedDate = formatDate(now, 'yyyy-MM-dd', 'en');
    console.log(formatDate(now, 'yyyy-MM-dd', 'en'));

  }



}
