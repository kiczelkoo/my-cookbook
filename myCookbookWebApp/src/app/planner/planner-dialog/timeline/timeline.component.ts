import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { formatDate } from '@angular/common';
import { DateModel } from './date-model';
import { toLongDateFormat, toShortDateFormat, calculateTimeRange } from '../../date-util';

@Component({
  selector: 'mc-app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {

  @Output() newDateSelected = new EventEmitter<Date>();

  displaydDates: DateModel[] = [];

  constructor() { }

  ngOnInit(): void {
    const now = new Date();
    this.generateDatesInRange(now);
    this.newDateSelected.emit(now);
  }

  onSelectDate(newSelectedDate: DateModel) {
    this.displaydDates = [];
    this.generateDatesInRange(newSelectedDate.date);
    this.newDateSelected.emit(newSelectedDate.date);
  }

  private generateDatesInRange(selected: Date) {
    const dateRangeShift: number = 4;
    const timeRange: [Date, Date] = calculateTimeRange(selected, dateRangeShift);
    const from = timeRange[0];
    const to = timeRange[1];
    while (from <= to) {
      this.displaydDates.push({
        date: new Date(from),
        longDate: toLongDateFormat(from),
        shortDate: toShortDateFormat(from),
        isSelected: toLongDateFormat(from) === toLongDateFormat(selected)
      });
      from.setDate(from.getDate() + 1);
    }
  }
}
