## GdxLearningGame
An Android and Desktop game created as a prototype to help myself learn to use IntelliJ IDEA, the Android SDK, and libGDX.

## Contributing

1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download), [Eclipse](https://www.eclipse.org/downloads/), [Android Studio](https://developer.android.com/studio), or any IDE of your choice 
3. Install the Java JDK 8+ and [Android SDK](https://developer.android.com/studio/releases/sdk-tools) (if not already installed)
4. Clone this repository with `git clone https://github.com/qwertysam/GdxLearningGame.git`
5. Open the project in an IDE, and sync Gradle to download all the dependencies
5. The main source files are found in `GdxLearningGame/core/src`

Now you can run the game!

#### Troubleshooting
- If you are getting errors from Gradle about not being able to compile the :android project, you must either configure your system's ANDROID_HOME or create the file `GdxLearningGame/local.properties` and add the line `sdk.dir=/path/to/your/Android/Sdk`
- If running the game from an IDE is failing to load the assets, you may need to change your Run/Debug configuration to have the working directory set to `GdxLearningGame/android/assets` (this is because the desktop and android modules both rely on the same Android assets folder)
- When exporting as a JAR file, you may need to...
  - Copy the files from `/GdxLearningGame/android/assets/` into the JAR archive
  - In the JAR archive, edit `META-INF/MANIFEST.MF` and add the following line if it doesn't exist `Main-Class: net.qwertysam.desktop.DesktopLauncher`
