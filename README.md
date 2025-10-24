2048 Game - Backend

Spring Boot backend for the 2048 game implementation. Provides REST API for game logic and state management!

Live Demo
Frontend Application: https://2048-matrix-game.netlify.app/
Backend API: https://web-production-45b5.up.railway.app/api/2048

Tech Stack
Framework: Spring Boot 3.5.7
Java Version: 21
Build Tool: Gradle
Deployment: Railway
CORS: Configured for production and development

API Endpoints

Start
POST /api/2048/start?size=4
Starts a new game with specified board size (default: 4x4)

Move
POST /api/2048/move?direction={direction}
Available Directions:
up
down
left
right

State
GET /api/2048/state

Restart
POST /api/2048/restart?size=4

Railway Deployment
This project is configured for automatic deployment on Railway:
system.properties - Specifies Java 21 runtime
Procfile - Defines the startup command

API Response Format
{
  "matrix": [[0,2,0,0],[0,0,2,0],[0,0,0,0],[0,0,0,0]],
  "score": 0,
  "gameOver": false,
  "won": false,
  "matrixSize": 4
}

CORS Settings
Production: https://2048-matrix-game.netlify.app
Development: http://localhost:3000

Server Port
Default: 8080
Railway: Uses $PORT environment variable

