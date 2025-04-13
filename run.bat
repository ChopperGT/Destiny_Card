@echo off
setlocal

:: Vérifier Java
where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo Java non trouvé. Veuillez installer Java JDK 23.
    pause
    exit /b 1
)

:: Nettoyer les anciens fichiers compilés
if exist "build" rd /s /q "build"

:: Créer les dossiers nécessaires
mkdir "build\classes"
mkdir "build\resources"

:: Compiler
echo Compilation...
javac -d build\classes ^
    --module-path "src\lib" ^
    --add-modules javafx.controls,javafx.media,javafx.fxml,javafx.graphics,javafx.base ^
    -cp "src\lib\gson-2.12.1.jar" ^
    src\Main_Menu.java ^
    src\Code\Menu\*.java ^
    src\Code\Game\Player\*.java

if %ERRORLEVEL% neq 0 (
    echo Erreur de compilation
    pause
    exit /b 1
)

:: Copier les ressources
echo Copie des ressources...
xcopy /E /I /Y "src\Image" "build\resources\Image" >nul
xcopy /E /I /Y "src\Video" "build\resources\Video" >nul
xcopy /E /I /Y "src\Song" "build\resources\Song" >nul
xcopy /E /I /Y "src\Police" "build\resources\Police" >nul

:: Lancer
echo Lancement...
java ^
    -Djava.library.path="src\lib\bin_fx" ^
    --module-path "src\lib" ^
    --add-modules javafx.controls,javafx.media,javafx.fxml,javafx.graphics,javafx.base ^
    -cp "build\classes;build\resources;src\lib\gson-2.12.1.jar" ^
    Code.Menu.Main_Menu

pause 