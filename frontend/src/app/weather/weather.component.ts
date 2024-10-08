// src/app/weather/weather.component.ts

import { Component } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-weather',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatFormFieldModule,
    HttpClientModule
  ],
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.scss']
})
export class WeatherComponent {
  city: string = '';
  forecasts: any[] = [];

  constructor() { }

  fetchWeather() {
    fetch(`http://localhost:8080/api/weather/${this.city}`)
    .then((response) => response.json())
    .then((data) => {
      this.forecasts = data.list; // Assign forecast data
    })
    .catch((error) => {
      console.error('Error fetching weather data:', error);
    });
  }
}
