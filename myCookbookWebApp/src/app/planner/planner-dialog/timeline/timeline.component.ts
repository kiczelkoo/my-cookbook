import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';
import { DateModel } from './date-model';

@Component({
  selector: 'mc-app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {

  displaydDates: DateModel[] = [];

  constructor() { }

  ngOnInit(): void {
    const now = new Date();
    this.generateDateRanges(now);
  }

  onSelectDate(newSelectedDate: DateModel) {
    console.log(newSelectedDate);
    this.displaydDates = [];
    this.generateDateRanges(newSelectedDate.date);
  }

  private generateDateRanges(selected: Date) {
    const dateRangeShift: number = 4;

    const from = new Date(selected);
    from.setDate(selected.getDate() - dateRangeShift)

    const to = new Date(selected);
    to.setDate(selected.getDate() + dateRangeShift)

    while (from <= to) {
      this.displaydDates.push({
        date: new Date(from),
        longDate: formatDate(from, 'yyyy-MM-dd', 'en'),
        shortDate: formatDate(from, 'dd.MM', 'en'),
        isSelected: formatDate(from, 'yyyy-MM-dd', 'en') === formatDate(selected, 'yyyy-MM-dd', 'en')
      });
      from.setDate(from.getDate() + 1);
    }
  }
}
