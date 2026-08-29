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

## What It Does
- Server listens on port 12345

- Client connects and sends a message

- Server receives it, converts to uppercase, and sends it back

- Client displays the response

- Type quit to exit the client.

## Files
- Server.java – Listens for connections and echoes messages

- Client.java – Sends messages and displays responses

## What I Learned
- How TCP sockets work in Java

- Try-with-resources for clean resource management

- Basic client-server architecture

