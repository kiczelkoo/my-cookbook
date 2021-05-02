import { Component, Input, OnInit } from '@angular/core';
import { Meal } from '../../meal';

@Component({
  selector: 'mcb-meal',
  templateUrl: './meal.component.html',
  styleUrls: ['./meal.component.scss']
})
export class MealComponent implements OnInit {

  @Input()
  meal: Meal;

  constructor() { }

  ngOnInit(): void {
  }

}
