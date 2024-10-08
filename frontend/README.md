
# Weather App Frontend

This is the frontend part of the Weather App, developed using Angular. It allows users to get the weather forecast for a specific city.

## Features

- Input field for entering a city name.
- Displays the weather forecast including date, temperature, and weather description in a table.
- Responsive design using Angular Material.

## Getting Started

### Prerequisites

- Node.js (v14 or later)
- Angular CLI (v14 or later)

### Installation

1. Clone the repository:
   ```bash
   git clone <your-repository-url>
   cd weather-app-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

### Running the Application

To run the application locally, execute:
```bash
ng serve
```

Then navigate to `http://localhost:4200/` in your web browser.

### API Integration

The frontend interacts with the backend service API to fetch weather data. Ensure that the backend is running and accessible at `http://localhost:8080/api/weather/{city}`.

### CORS Configuration

If you encounter CORS issues, ensure that the backend service is properly configured to allow requests from the frontend's origin.

## Styling

The application uses Angular Material for UI components. Ensure that the Material styles are imported in your `styles.css` or `angular.json`:

```css
@import '~@angular/material/prebuilt-themes/indigo-pink.css';
```

## Components

- **WeatherComponent**: Displays the weather form and results.
- **AppComponent**: The main component that hosts the WeatherComponent.

## Conclusion

This frontend provides a simple and intuitive interface for users to check the weather forecast. It is built with Angular and utilizes Angular Material for a modern look and feel.

For more information, please refer to the backend README or contact the development team.

