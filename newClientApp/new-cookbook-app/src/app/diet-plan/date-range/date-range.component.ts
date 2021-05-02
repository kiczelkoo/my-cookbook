import { EventEmitter } from '@angular/core';
import { Component, Input, OnInit, Output } from '@angular/core';
import { MatButtonToggleChange } from '@angular/material/button-toggle';

@Component({
  selector: 'mcb-date-range',
  templateUrl: './date-range.component.html',
  styleUrls: ['./date-range.component.scss']
})
export class DateRangeComponent implements OnInit {

  dateRange: Date[] = [];

  get selectedDate(): Date { return this._selectedDate; }

  @Input()
  set selectedDate(selectedDate: Date) {
    this._selectedDate = selectedDate;
    this.dateRange = this.calculateDateRange(this._selectedDate);
  }
  private _selectedDate = new Date();

  @Output()
  newDateSelected = new EventEmitter<Date>();

  constructor() { }

  ngOnInit(): void {
  }

  calculateDateRange(startDate: Date): Date[] {
    const from = new Date(startDate);
    from.setDate(startDate.getDate() - 7);

    const sevenDaysInFuture = new Date(startDate);
    sevenDaysInFuture.setDate(startDate.getDate() + 7);

    const dates: Date[] = [];
    while (from <= sevenDaysInFuture) {
      dates.push(new Date(from));
      from.setDate(from.getDate() + 1);
    }
    return dates;
  }

  selectionChanged(changeEvent: MatButtonToggleChange) {
    this._selectedDate = changeEvent.value;
    this.newDateSelected.emit(changeEvent.value);
  }

  isChecked(day: Date): boolean {
    return this._selectedDate.getDate() === day.getDate();
  }

}
