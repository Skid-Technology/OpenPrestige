@echo off
setlocal
cd /d "%~dp0"

rem Gradle/Loom for 1.21.11 needs JDK 21 (default java may be newer)
if exist "C:\Program Files\Java\jdk-21" set "JAVA_HOME=C:\Program Files\Java\jdk-21"

if not exist logs mkdir logs
for /f %%t in ('powershell -NoProfile -Command "Get-Date -Format yyyy-MM-dd_HH-mm-ss"') do set "TS=%%t"
set "LOG=logs\run_%TS%.log"
echo Logging to %LOG%

rem Everything gradle/game prints goes to the console AND logs\run_<time>.log
rem extra args are passed through, e.g. run.bat -PuiCmd=run\cmd\c.txt
set "ARGS=%*"
powershell -NoProfile -Command "& { & '.\gradlew.bat' runClient --console=plain $env:ARGS 2>&1 | ForEach-Object { $_; Add-Content -LiteralPath '%LOG%' -Value $_ -Encoding UTF8 }; exit $LASTEXITCODE }"
set "RC=%ERRORLEVEL%"

rem keep a copy of the game's own latest.log and any crash report written during this run
if exist run\logs\latest.log copy /y run\logs\latest.log "logs\run_%TS%_latest.log" >nul
for /f "delims=" %%c in ('powershell -NoProfile -Command "Get-ChildItem run\crash-reports\*.txt -ErrorAction SilentlyContinue | Where-Object { $_.LastWriteTime.Ticks -gt %T0% } | Sort-Object LastWriteTime | Select-Object -Last 1 -ExpandProperty FullName"') do (
    copy /y "%%c" "logs\run_%TS%_crash.txt" >nul
    echo Crash report copied to logs\run_%TS%_crash.txt
)

echo Exit code %RC%. Log: %LOG%
if not "%RC%"=="0" pause
exit /b %RC%
