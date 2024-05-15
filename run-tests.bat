@echo off
REM Navigate to the root of your repository
cd %~dp0

REM Ensure environment variables are set
set JAVA_HOME=C:\Program Files\Java\jdk-1.8
set PATH=%JAVA_HOME%\bin;%PATH%

REM Check if Maven is installed and in the PATH
mvn -version
IF %ERRORLEVEL% NEQ 0 (
    echo Maven is not installed or not in the PATH. Please install Maven and add it to the PATH.
    pause
    exit /b 1
)

REM Run Maven command to clean and test
mvn clean test

REM Pause to keep the command prompt open after execution
pause
