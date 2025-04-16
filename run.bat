@echo off
setlocal enabledelayedexpansion

REM Vérifier si Java est installé
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo Java n'est pas installé ou n'est pas dans le PATH.
    echo Veuillez installer Java et réessayer.
    pause
    exit /b 1
)

REM Afficher la version de Java pour s'assurer qu'elle est correcte
echo Version de Java utilisée:
java -version
echo.

REM Nettoyer et recréer le dossier build
echo Nettoyage des anciens fichiers...
if exist "build" (
    rmdir /s /q "build"
)
mkdir "build"
mkdir "build\classes"
mkdir "build\resources"

REM Créer la structure des dossiers de ressources
echo Création de la structure des ressources...
if not exist "src\resources" (
    mkdir "src\resources"
    mkdir "src\resources\Police"
    mkdir "src\resources\Video"
    mkdir "src\resources\Song"
)

REM Copier les ressources existantes
echo Copie des ressources existantes...
if exist "src\Police\*" (
    xcopy /y "src\Police\*" "src\resources\Police\"
)
if exist "src\Video\*" (
    xcopy /y "src\Video\*" "src\resources\Video\"
)
if exist "src\Song\*" (
    xcopy /y "src\Song\*" "src\resources\Song\"
)

REM Vérification du répertoire src\Code\Menu
if not exist "src\Code\Menu\Main_Menu.java" (
    echo ERREUR: Le fichier Main_Menu.java est introuvable!
    echo Vérifiez que le fichier existe à l'emplacement: src\Code\Menu\Main_Menu.java
    pause
    exit /b 1
)

REM Compiler les fichiers Java
echo Compilation des fichiers Java...

REM Créer les répertoires de destination pour préserver la structure des packages
mkdir "build\classes\Code"
mkdir "build\classes\Code\Menu"
mkdir "build\classes\Code\Save"
mkdir "build\classes\Code\Save\Appli"
mkdir "build\classes\Code\Game"
mkdir "build\classes\Code\Game\Gameplay"
mkdir "build\classes\Code\Game\Gameplay\Card"
mkdir "build\classes\Code\Game\Gameplay\Card\Monstre"

REM Créer un répertoire pour Scène sans utiliser le caractère accentué directement
mkdir "build\classes\Code\Game\Scene"
mkdir "build\classes\Code\Game\Scene\Novel"
mkdir "build\classes\Code\Game\Player"

REM Trouver et compiler tous les fichiers Java
echo Compilation de tous les fichiers Java...
set JAVAC_OPTS=-encoding UTF-8 -cp "src;src\lib\*" --module-path "src\lib" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.web,com.google.gson -d build\classes
set JAVAFX_MODULES=javafx.controls,javafx.fxml,javafx.media,javafx.web,com.google.gson

REM Vérifier si les fichiers javafx existent
if not exist "src\lib\javafx.base.jar" (
    echo ERREUR: Les bibliothèques JavaFX sont introuvables!
    echo Vérifiez que les fichiers .jar de JavaFX sont présents dans le dossier src\lib
    pause
    exit /b 1
)

REM Créer une liste temporaire des fichiers à compiler
echo Création de la liste des fichiers à compiler...
dir /b /s src\*.java > javalist.txt

REM Compiler tous les fichiers Java en un seul appel
echo Compilation des fichiers...
javac %JAVAC_OPTS% @javalist.txt
set COMPILE_EXIT_CODE=%ERRORLEVEL%

REM Vérifier si on a trouvé des fichiers dans le répertoire Scène
echo Compilation spécifique des fichiers dans Scène\Novel...
if exist "src\Code\Game\Scène\Novel\*.java" (
    echo Les fichiers Java dans Scène\Novel existent, les compilant manuellement...
    for %%f in (src\Code\Game\Scène\Novel\*.java) do (
        echo Compilation de %%f
        javac %JAVAC_OPTS% "%%f"
    )
) else (
    echo Répertoire Scène\Novel non trouvé ou sans fichiers Java
)

REM S'assurer que les classes principales sont bien compilées
echo Compilation des fichiers principaux...
if exist "src\Code\Menu\Main_Menu.java" (
    javac %JAVAC_OPTS% "src\Code\Menu\Main_Menu.java"
)
if exist "src\Code\Menu\Credit.java" (
    javac %JAVAC_OPTS% "src\Code\Menu\Credit.java"
)

del javalist.txt

if %COMPILE_EXIT_CODE% neq 0 (
    echo Erreur de compilation
    pause
    exit /b 1
)

REM Vérifier si la classe a bien été compilée
if not exist "build\classes\Code\Menu\Main_Menu.class" (
    echo ERREUR: La classe Main_Menu n'a pas été compilée correctement!
    echo Vérifiez que le package est correctement déclaré dans Main_Menu.java
    pause
    exit /b 1
)

REM Copier les ressources dans le dossier build
echo Copie des ressources dans le dossier build...
xcopy /s /y "src\resources\*" "build\resources\"

REM Copier les images des boîtes et des cartes dans build
echo Copie des images...
mkdir "build\Image"
mkdir "build\Image\Box"
mkdir "build\Image\Card"
if exist "src\Image\Box\*" (
    xcopy /s /y "src\Image\Box\*" "build\Image\Box\"
)
if exist "src\Image\Card\*" (
    xcopy /s /y "src\Image\Card\*" "build\Image\Card\"
)

REM Définir le classpath
set CLASSPATH=.;build\classes;build\resources;src\lib\*;

echo Contenu du dossier build\classes\Code\Menu:
dir build\classes\Code\Menu

echo Lancement de l'application...
java -Djava.library.path="src\lib\bin_fx" --module-path "src\lib" --add-modules %JAVAFX_MODULES% --enable-native-access=javafx.graphics,javafx.media -cp "%CLASSPATH%" Code.Menu.Main_Menu

if %ERRORLEVEL% neq 0 (
    echo Erreur lors de l'exécution de l'application
    echo Vérifiez que la classe Code.Menu.Main_Menu existe et qu'elle contient une méthode main.
    echo Tentative alternative:
    java -cp "build\classes" Code.Menu.Main_Menu
)

pause 