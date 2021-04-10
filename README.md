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
7. avviare il dispositivo e abilitare la modalità sviluppatore.

## Effettuazione test
Di seguito si mostrerà come utilizzare gli ambienti sopraccitati per condurre i test su ciascuna delle due applicazioni AR.

### Safari Animal AR
Di seguito saranno definiti i passi da seguire per importare l'applicazione Safari Animal AR in locale. Si noti che quanto sarà spiegato nella sezione Import Applicazione potrà essere bypassato scaricando direttamente il [file apk](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/applicazione/safari.apk), al fine di installarlo sul dispositivo virtuale mediante drag and drop. 
#### Import applicazione
E' necessario scaricare l'intero repository [Safari AR](https://github.com/abdullahibneat/SafariAnimalsAR) e seguire le istruzioni della sezione How to use del medesimo repository. Successivamente bisognerà cambiare il marker seguendo i successivi passi:
1. aprire il progetto con UnityHub;
2. selezionare il GameObject Image Target dalla gerarchia di oggetti;
3. dalla sezione Inspector, alla voce Database selezionare VuforiaMars_Images e alla voce Image Target selezionare Astronaut;

A questo punto, dopo aver estratto dallo zip Poco-SDK dallo zip, è necessario importare tramite drag and drop all'interno del progetto, preferibilmente nella cartella Script, la cartella Unity3D e successivamente al suo interno sarà necessario cancellare le cartelle ngui e fairygui. Dopodichè bisognerà aggiungere lo script PocoManager alla ARCamera. In caso di errori assicurarsi che nella sezione Player Settings, nella tab Other Settings alla voce Scripting Runtime Version sia selezionato .Net 4.x Equivalent. Per quanto riguarda gli script, nella cartella Script del progetto Unity bisognerà aggiungere, mediante drag and drop, il file [SondaManager](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/script%20con%20sonde/SondaManager.cs) e invocare la sua funzione per negli altri script per inserire le sonde. Alternativamente è possibile sostituire i file presenti con quelli nella cartella [Script con sonde](https://github.com/danilobevilacqua/TesiMagistrale/tree/main/Safari%20Animal%20AR/script%20con%20sonde). Infine sarà necessario creare l'apk che dovrà essere seployato sul dispositivo virtuale e per farlo sono necessari i successivi passi:
1. aprire Android Studio e avviare il dispositivo emulato precedentemente creato.
2. dopo il suo avvio, in Unity cliccare sulla tab File e successivamente su Buil Settings
3. cliccare su Android e poi su Switch Platform
4. alla voce Run Device selezionare il dispositivo emulato
5. cliccare su Build And Run

Se tutto è andato a buon fine sul dispositivo emulato dovrebbe avviarsi l'applicazione. A questo punto sarà necessario posizionare il [marker](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/marker.jpg) sul tavolo o sulla parete della stanza virtuale. Per farlo bisognerà semplicemente cliccare sui tre puntini della barra associata al dispositivo. Nella nuova finestra che si aprirà selezionare la tab Camera e posizionare il marker preferibilmente sul tavolo e chiudere questa finestra. Spostarsi fino alla stanza in cui è presente il marker, inquadrarlo al fine di far comparire la scena di realtà aumentata. Si noti che lo sviluppatore ha previsto che tale applicazioni fissi l'orientamento dello smartphone in orizzontale.

#### Test
Per effettuare i test sarà necessario aprire il la cartella AirTestIDE (precedentemente posizionata sul Desktop) e avviare AirTestIDE.exe. all'avvio sarà necessario cliccare sul tasto skip della finestra che si aprirà oltre al prompt dei comandi. Una volta aperto l'IDE cliccare sulla tab File e successivamente Open per aprire lo script di test relativo al criterio di copertura desiderato. Prima di avviarlo:
* assicurasi che il telefono sia collegato All'IDE. Per farlo basta cliccare sul tasto connect del accanto al nome del dispositivo Nella finestra Devices. Qualora il dispositivo non comparisse cliccare su refresh ADB;
* assicurarsi che l'applicazione appena avviata punti il marker creando la scena di realtà aumentata;
* assicurarsi di cancellare un eventuale precedente file di Log dalla memoria dello smartphone. Per farlo è necessario:
  - cliccare su Settings;
  - cliccare su Storage;
  - cliccare su Internal shared Storage;
  - cliccare su Files;
  - cliccare su Android;
  - cliccare su Data;
  - cliccare su com.abdu.PointAR;
  - cliccare su files;
  - eliminare LogFiles.txt.

Dopo aver effettuato i sopraccitati controlli, avviare lo script di test e attenderne la fine. Per visualizzare il report cliccare sulla tab Run e successivamente su View Report. Nella stessa cartella dello script sarà creato il file delle interazioni che definirà le interazioni effettuate per ogni path eseguito. A questo punto è fondamentale estrarre il file di log dallo smartphone. Per farlo è necessario utilizzare il programma [Analisi Log](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Analisi%20Log.jar), avendo cura di non chiudere il dispositivo emulato, effettuando i seguenti step:
1. avviare Analisi Log;
2. cliccare sul bottone Importa;
3. inserire come nome package com.abdu.SafariAR;
4. inserire come nome del file di log un qualsiasi nome che non contenga spazi e\o simboli;
5. cliccare sul bottone Importa.

Se tutto sarà andato a buon fine, sarà presente un messaggio di avvenuto caricamento, il file di log sarà stato cancellato dal dispositivo emulato e sarà presente sul desktop con il nome scelto. A questo punto sarà possibile caricare gli script al fine di valutarne la copertura. Per farlo è necessario cliccare sul bottone Carica Script e inserire i file,contenenti le sonde, da valutare (Autofocus, AnimalSpawn, AnimalsController). Dopo averli caricati sarà necessario cliccare su Analizza. Fatto ciò si aprirà una finestra che mostra quali rami sono stati eseguiti e quante volte e la percentuale di copertura dello script (rami eseguiti / rami totali). Nel caso in cui la percentuale di copertura fosse inferiore al 100% per ogni script sarà possibile cliccare il tasto Rami non eseguiti, che mostrerà quali rami del codice non sono stati eseguiti. Quanto descritto può essere effettuato per ognuno degli script di test (testAllTransionsCoverageNew.air, testAllTransionsCoverageEstesoNew.air, testAllStateCoverageNew.air, testAllOneLoopPathsCoverageNew.air). Si noti che è possibile bypassare l'esecuzione del test scaricando i log e i file delle interazioni da [qui](https://github.com/danilobevilacqua/TesiMagistrale/tree/main/Safari%20Animal%20AR/applicazione). Sarà possibile analizzare il file di log utilizzando il bottone Carica del programma Analisi Log che permette di caricare il file non dal dispositivo emulato ma dal PC.
 

   


































