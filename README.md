# Personalized Learning Experience

This is an Android application that provides a personalized learning experience for users by generating quizzes and tracking their progress.

## Features

- **User Registration & Login:** Secure sign-up and login screens.
- **Personalized Dashboard:** View tasks, generate new quizzes, and see pending tasks.
- **Quiz Generation:** AI-generated quizzes tailored to user interests.
- **Quiz Attempt & Results:** Users can attempt quizzes and view detailed results, including correct/incorrect answers.
- **Interest Selection:** Users can select their learning interests to personalize content.

## Getting Started

### Prerequisites

- Android Studio (latest version recommended)
- Android device or emulator (API 24+)
- Internet connection (for quiz fetching)

### Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/personalized_learning_experience.git
   cd personalized_learning_experience
   ```

2. **Open in Android Studio:**
   - Open Android Studio.
   - Select "Open an existing project" and choose this directory.

3. **Build the project:**
   - Let Gradle sync and download dependencies.
   - Build the project (Build > Make Project).

4. **Run the app:**
   - Connect your device or start an emulator.
   - Click the Run button or use `Shift+F10`.

### API

- The app fetches quizzes from:  
  `https://llamamobileappdevelopment.pythonanywhere.com/getQuiz`
- Make sure your device/emulator has internet access.

## Project Structure

- `app/src/main/java/com/example/personalized_learning_experience/`
  - `MainActivity.java` - Entry point
  - `LoginActivity.java` - Login screen
  - `RegisterActivity.java` - Registration screen
  - `DashboardActivity.java` - User dashboard
  - `TaskActivity.java` - Quiz/Task screen
  - `ResultsActivity.java` - Quiz results screen
  - `QuizQuestion.java`, `QuizOption.java` - Data models
- `app/src/main/res/layout/` - XML layout files for each screen

## Dependencies

- [Gson](https://github.com/google/gson) for JSON parsing
- AndroidX libraries
- Material Components

## Customization

- Update the API endpoint in `TaskActivity.java` if your backend changes.
- Modify layouts in `res/layout/` for UI changes.

## License

This project is for educational purposes. 