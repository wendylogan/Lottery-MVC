# Lottery Simulator

A Swing-based GUI app that runs lottery simulations and tracks how often your chosen numbers match the draw.

I built this to practice the MVC pattern and get more comfortable with Java Swing. The app lets you enter six numbers, run multiple simulations, and see how many times each match count (0-5) occurs.

## How to Use

1. Enter six numbers (1-60) separated by spaces
2. Enter how many simulations to run (1-100,000)
3. Click "Run Lottery"
4. The results show how many simulations matched 0, 1, 2, etc. of your numbers

## Running Locally

```bash
javac *.java
java Main
```

## Files

- View.java – Handles the GUI layout and user interaction

- Controller.java – Bridges the view and model, processes input

- Model.java – Contains the simulation logic and data

- Main.java – Entry point for the application

## What I Learned/Practiced

- MVC Pattern – Separating the logic, data, and UI made the code easier to manage and test

- Java Swing – Putting together a functional GUI with panels, text fields, and event listeners

- User Input Validation – Making sure the app handles invalid entries gracefully

- Simulation Logic – Running thousands of lottery draws efficiently and tracking match results