# Testing di applicazioni AR Mobile
Prima di descrivere i passi mediante i quali replicare i test condotti, si illustrano i file e le cartelle presenti all'interno del repository. All'inizio della gerarchia sono presenti due cartelle e un file:
* Analisi Log, file .jar, necessario per l'analisi dei file di log (come sarà mostrato di seguito);
* Safari Animal AR;
* Point AR.

Le due cartelle sono relative alle omonime applicazioni applicazioni (raggiungibili al link [Safari AR](https://github.com/abdullahibneat/SafariAnimalsAR) e [Point AR](https://github.com/abdullahibneat/PointAR)), che sono state utilizzate come casi di studio. All'interno della cartella Safari Animal AR è presente:
* la cartella applicazione contentiene l'apk e i file di log e delle interazioni per ciascuna tipologia di copertura (entrambi all'interno della rispettiva cartella);
* la cartella mutante 1 contiene l'apk dell'applicazione modificata (è stato iniettato l'errore di movimento, ovvero spostando il pad a destra l'animale si sposta a sinistra e viceversa), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella mutante 2 contiene l'apk dell'applicazione modificata (è stato iniettato l'errore di molteplice presenza di animali sulla scena, ossia scegliendo un qualsiasi animale che non sia la zebra esso non sarà solo sulla scena ma sarà compresente con la zebra), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella script con sonde contiene gli script originali dell'applicazione a cui sono state aggiunte le sonde per la verifica della copertura del codice e lo script che ne permette l'inserimento (SonadaManager);
* le diverse tipologie di test condotti (testAllOneLoopPathsCoverageNew.air, testAllStateCoverageNew.air, testAllTransitionsCoverageNew.air e testAllTransitionsCoverageEstesoNew.air);
* il marker.

Similmente all'interno della cartella Point AR è presente:
* la cartella applicazione contentiene l'apk e i file di log e delle interazioni per ciascuna tipologia di copertura (entrambi all'interno della rispettiva cartella);
* la cartella mutante 1 contiene l'apk dell'applicazione modificata (è stato iniettato l'errore mancata apertura del menù a tendina, ovvero non è possibile aprirlo per selezionare la lingua), i file delle interazioni all'interno delle rispettive cartelle e l'immagine che mostra quale spunta sia stata tolta dall'IDE di Unity per causare tale errore;
* la cartella mutante 2 contiene l'apk dell'applicazione modificata (è stato iniettato l'errore di errata traduzione del marker 1, ossia selezionando la lingua lituana il marker 1 sarà tradotto in italiano), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella script con sonde contiene gli script originali dell'applicazione a cui sono state aggiunte le sonde per la verifica della copertura del codice e lo script che ne permette l'inserimento (SonadaManager);
* le diverse tipologie di test condotti (testAllOneLoopPathsCoverageAttesa.air, testAllOneLoopPathsCoverageMarker1.air, testAllOneLoopPathsCoverageMarker2.air, testAllStatesCoverageMarker1.air, testAllStatesCoverageMarker2.air, testAllTransitionsCoverageAttesa.air, testAllTransitionsCoverageMarker1.air, testAllTransitionsCoverageMarker2.air);
* i marker.

Descritto il contenuto del repository, si passa a dettagliare come replicare quanto descritto nella tesi.

## Installazione Tools
Si comincia installando gli ambienti necessari:
1. installazione dell'engine unity versione 2018.4.30f1, scaricabile dal [sito ufficiale](https://unity3d.com/get-unity/download/archive). E' Necessario intallare anche UnityHub (sempre dal medesimo link) poichè esso permette la gestione di diverse versioni di Unity e l'aggiunta di eventuali moduli;
2. dopo aver installato UnityHub, avviarlo e scegliere la tab installs, cliccare i tre pallini relativi al box della versione Unity, cliccare Add Modules dal menù a tendina, spuntare Vuforia Augmented Reality e Support e Android Build Support, infine cliccare Done. Una volta terminata l'installazione, nel box della versione Unity compariranno le relative icone dei moduli installati;
3. scaricare AirTestIDE dal [sito ufficiale](https://airtest.netease.com/), estrarre il contenuto dello zip e posizionarlo sul Desktop. E' necessario posizionarlo sul Desktop al fine di permettere il corretto funzionamento degli script di test e del .jar Analisi Log;
4. scaricara Poco-SDK dal [sito ufficiale](https://github.com/AirtestProject/Poco-SDK);
5. installare Android Studio dal [sito ufficiale](https://developer.android.com/studio);
6. dopo aver installato android studio, avviarlo e creare un dispositivo virtuale con le seguenti caratteristiche:
   - modello smartphone: Pixel 2;
   - sistema operativo: Android 8.1 Oreo;
   - Livello API: 27;
   - RAM: 1536 MB;
   - Spazio di Archiviazione: 2048 MB interno + 512 MB SD-CARD.

## Effettuazione test
Di seguito si mostrerà come utilizzare gli ambienti sopraccitati per condurre i test su ciascuna delle due applicazioni AR.

### Safari Animal AR
Di seguito saranno definiti i passi da seguire per importare l'applicazione Safari Animal AR in locale. Si noti che quanto sarà spiegato potrà essere bypassato scaricando direttamente il [file apk](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/applicazione/safari.apk). E' necessario scaricare dal repository [Safari AR](https://github.com/abdullahibneat/SafariAnimalsAR) e seguire le istruzioni della sezione How to use del medesimo repository. Successivamente bisognerà cambiare il marker seguendo i successivi passi:
1. aprire il progetto con UnityHub;
1. selezionare il GameObject Image Target dalla gerarchia di oggetti;
2. dalla sezione Inspector, alla voce Database selezionare VuforiaMars_Images e alla voce Image Target selezionare Astronaut;

A questo punto, dopo aver estratto dallo zip Poco-SDK dallo zip, è necessario importare tramite drag & drop all'interno del progetto, preferibilmente nella cartella Script, la cartella Unity3D e successivamente al suo interno sarà necessario cancellare le cartelle ngui e fairygui. Dopodichè bisognerà aggiungere lo script PocoManager alla ARCamera. In caso di errori assicurarsi che nella sezione Player Settings, nella tab Other Settings alla voce Scripting Runtime Version sia selezionato .Net 4.x Equivalent. Per quanto riguarda gli script, nella cartella Script del progetto Unity bisognerà aggiungere gli script contenti le sonde 

































