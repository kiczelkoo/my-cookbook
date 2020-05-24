import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from '../../day-plan-model';

@Component({
  selector: 'mc-app-meal',
  templateUrl: './meal.component.html',
  styleUrls: ['./meal.component.scss']
})
export class MealComponent implements OnInit {

  @Input()
  meal: Recipe;

  constructor() { }

  ngOnInit(): void {
  }

}
