import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { DietPlanComponent } from './diet-plan/diet-plan.component';
import { DayPlanComponent } from './diet-plan/day-plan/day-plan.component';
import { DateRangeComponent } from './diet-plan/date-range/date-range.component';
import { DaySummaryComponent } from './diet-plan/day-plan/day-summary/day-summary.component';
import { MealComponent } from './diet-plan/day-plan/meal/meal.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MealSummaryComponent } from './diet-plan/day-plan/meal/meal-summary/meal-summary.component';
import { DishComponent } from './diet-plan/day-plan/meal/dish/dish.component'

@NgModule({
  declarations: [
    AppComponent,
    DietPlanComponent,
    DayPlanComponent,
    DateRangeComponent,
    DaySummaryComponent,
    MealComponent,
    MealSummaryComponent,
    DishComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonToggleModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
