import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { DietPlanComponent } from './diet-plan/diet-plan.component';
import { DayPlanComponent } from './diet-plan/day-plan/day-plan.component';
import { DateRangeComponent } from './diet-plan/date-range/date-range.component';

@NgModule({
  declarations: [
    AppComponent,
    DietPlanComponent,
    DayPlanComponent,
    DateRangeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonToggleModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
