import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Dish } from 'src/app/diet-plan/dish';

@Component({
  selector: 'mcb-dish',
  templateUrl: './dish.component.html',
  styleUrls: ['./dish.component.scss']
})
export class DishComponent implements OnInit {

  @Input()
  dish: Dish;

  constructor() { }

  ngOnInit(): void {
  }

}
