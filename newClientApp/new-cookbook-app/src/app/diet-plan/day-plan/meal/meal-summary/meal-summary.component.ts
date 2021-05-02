import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'mcb-meal-summary',
  templateUrl: './meal-summary.component.html',
  styleUrls: ['./meal-summary.component.scss']
})
export class MealSummaryComponent implements OnInit {

  @Input()
  kcal: number;

  @Input()
  carbs: number;

  @Input()
  fats: number;

  @Input()
  proteins: number;

  @Input()
  prepTime: number;

  @Input()
  mealName: string;

  constructor() { }

  ngOnInit(): void {
  }

}
