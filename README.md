# Testing di applicazioni AR Mobile
Prima di descrivere i passi mediante i quali replicare i test condotti, si illustrano i file e le cartelle presenti all'interno del repository. All'inizio della gerarchia sono presenti tre cartelle:
* analisi log, contenente il file .jar, necessario per l'analisi dei file di log (come sarà mostrato di seguito), e la cartella del relativo codice sorgente;
* Safari Animal AR;
* Point AR.

Le due cartelle, Safari Animal AR e Point AR, sono relative alle omonime applicazioni ([Safari AR](https://github.com/abdullahibneat/SafariAnimalsAR) e [Point AR](https://github.com/abdullahibneat/PointAR)) che sono state utilizzate come casi di studio. All'interno della cartella Safari Animal AR è presente:
* la cartella applicazione contenente l'apk e i file di log e delle interazioni per ciascuna tipologia di copertura (entrambi all'interno della rispettiva cartella);
* la cartella mutante 1 contenente l'apk dell'applicazione modificata (è stato iniettato l'errore di movimento, ovvero spostando il pad a destra l'animale si sposta a sinistra e viceversa), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella mutante 2 contenente l'apk dell'applicazione modificata (è stato iniettato l'errore di molteplice presenza di animali sulla scena, ossia scegliendo un qualsiasi animale, che non sia la zebra, esso non sarà solo sulla scena ma sarà compresente con la zebra), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella script con sonde contenente gli script originali dell'applicazione a cui sono state aggiunte le sonde per la verifica della copertura del codice e lo script che ne permette l'inserimento (SonadaManager);
* le diverse tipologie di test condotti (testAllOneLoopPathsCoverageNew.air, testAllStateCoverageNew.air, testAllTransitionsCoverageNew.air e testAllTransitionsCoverageEstesoNew.air);
* il marker.

Similmente all'interno della cartella Point AR è presente:
* la cartella applicazione contenente l'apk e i file di log e delle interazioni per ciascuna tipologia di copertura (entrambi all'interno della rispettiva cartella);
* la cartella mutante 1 contenente l'apk dell'applicazione modificata (è stato iniettato l'errore di mancata apertura del menù a tendina, ovvero non è possibile aprirlo per selezionare la lingua della traduzione), i file delle interazioni all'interno delle rispettive cartelle e l'immagine che mostra quale spunta sia stata tolta dall'IDE di Unity per causare tale errore;
* la cartella mutante 2 contenente l'apk dell'applicazione modificata (è stato iniettato l'errore di errata traduzione del marker 1, ossia selezionando la lingua lituana il marker 1 sarà tradotto in italiano), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella script con sonde contenente gli script originali dell'applicazione a cui sono state aggiunte le sonde per la verifica della copertura del codice e lo script che ne permette l'inserimento (SonadaManager);
* le diverse tipologie di test condotti (testAllOneLoopPathsCoverageAttesa.air, testAllOneLoopPathsCoverageMarker1.air, testAllOneLoopPathsCoverageMarker2.air, testAllStatesCoverageMarker1.air, testAllStatesCoverageMarker2.air, testAllTransitionsCoverageAttesa.air, testAllTransitionsCoverageMarker1.air, testAllTransitionsCoverageMarker2.air);
* i marker.

Descritto il contenuto del repository, si passa a dettagliare come replicare quanto descritto nella tesi.

## Installazione Tools
Si comincia installando gli ambienti necessari:
1. installazione dell'engine Unity versione 2018.4.30f1, scaricabile dal [sito ufficiale](https://unity3d.com/get-unity/download/archive). E' Necessario installare anche UnityHub (sempre dal medesimo link) poiché esso permette la gestione di diverse versioni di Unity e l'aggiunta di eventuali moduli;
2. dopo aver installato UnityHub, avviarlo e scegliere la tab `installs`, cliccare i tre pallini relativi al box della versione Unity, cliccare `Add Modules` dal menù a tendina, spuntare `Vuforia Augmented Reality` e `Android Build Support`, infine cliccare `Done`. Una volta terminata l'installazione, nel box della versione Unity compariranno le relative icone dei moduli installati;
3. scaricare AirTestIDE dal [sito ufficiale](https://airtest.netease.com/), estrarre il contenuto dello zip e posizionarlo sul Desktop. E' necessario posizionarlo sul Desktop al fine di permettere il corretto funzionamento degli script di test e del .jar Analisi Log;
4. scaricare Poco-SDK dal [sito ufficiale](https://github.com/AirtestProject/Poco-SDK);
5. installare Android Studio dal [sito ufficiale](https://developer.android.com/studio);
6. dopo aver installato Android studio, avviarlo e creare un dispositivo virtuale con le seguenti caratteristiche:
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
E' necessario scaricare, in formato zip, l'intero repository [Safari AR](https://github.com/abdullahibneat/SafariAnimalsAR) e seguire le istruzioni della sezione `How to use` del medesimo repository. Successivamente bisognerà cambiare il marker seguendo i successivi passi:
1. aprire il progetto con UnityHub;
2. selezionare il GameObject `Image Target` dalla gerarchia di oggetti;
3. dalla sezione Inspector, alla voce Database selezionare `VuforiaMars_Images` e alla voce Image Target selezionare `Astronaut`;

A questo punto, dopo aver estratto dallo zip Poco-SDK dallo zip, è necessario importare tramite drag and drop all'interno del progetto, preferibilmente nella cartella `_Script`, la cartella `Unity3D` e successivamente al suo interno sarà necessario cancellare le cartelle `ngui` e `fairygui`. Dopodiché bisognerà aggiungere lo script `PocoManager` alla `ARCamera`. In caso di errori assicurarsi che nella sezione `Player Settings`, nella tab `Other Settings` alla voce Scripting Runtime Version sia selezionato `.Net 4.x Equivalent`. Per quanto riguarda gli script, nella cartella `_Script` del progetto Unity bisognerà aggiungere, mediante drag and drop, il file [SondaManager](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/script%20con%20sonde/SondaManager.cs) e invocare la sua funzione negli altri script per inserire le sonde. Alternativamente è possibile sostituire i file presenti con quelli nella cartella [Script con sonde](https://github.com/danilobevilacqua/TesiMagistrale/tree/main/Safari%20Animal%20AR/script%20con%20sonde). Infine sarà necessario creare l'apk che dovrà essere deployato sul dispositivo virtuale e per farlo sono necessari i successivi passi:
1. aprire Android Studio e avviare il dispositivo emulato precedentemente creato;
2. dopo il suo avvio, in Unity cliccare sulla tab `File` e successivamente su `Buil Settings`;
3. cliccare su `Android` e poi su `Switch Platform`;
4. alla voce Run Device selezionare il dispositivo emulato;
5. cliccare su `Build And Run`.

Se tutto è andato a buon fine sul dispositivo emulato dovrebbe avviarsi l'applicazione. A questo punto sarà necessario posizionare il [marker](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/marker.jpg) sul tavolo o sulla parete della stanza virtuale. Per farlo bisognerà semplicemente cliccare sui tre puntini della barra associata al dispositivo. Nella nuova finestra che si aprirà selezionare la tab `Camera` e posizionare il marker, preferibilmente sul tavolo, e chiudere questa finestra. Spostarsi fino alla stanza in cui è presente il marker, inquadrarlo al fine di far comparire la scena di realtà aumentata. Si noti che lo sviluppatore ha previsto che tale applicazione fissi l'orientamento dello smartphone in orizzontale.

#### Test
Per effettuare i test sarà necessario aprire il la cartella `AirTestIDE` (precedentemente posizionata sul Desktop) e avviare `AirTestIDE.exe`. All'avvio sarà necessario cliccare sul tasto `skip` della finestra che si aprirà oltre al prompt dei comandi. Una volta aperto l'IDE cliccare sulla tab `File` e successivamente `Open` per aprire lo script di test relativo al criterio di copertura desiderato. Prima di avviarlo:
* assicurarsi che il telefono sia collegato All'IDE. Per farlo basta cliccare sul tasto `connect` accanto al nome del dispositivo nella finestra Devices. Qualora il dispositivo non comparisse cliccare su ``refresh ADB`;
* assicurarsi che l'applicazione appena avviata punti il marker creando la scena di realtà aumentata;
* assicurarsi di cancellare un eventuale precedente file di Log dalla memoria dello smartphone. Per farlo è necessario:
  - cliccare su `Settings`;
  - cliccare su `Storage`;
  - cliccare su `Internal shared Storage`;
  - cliccare su `Files`;
  - cliccare su `Android`;
  - cliccare su `Data`;
  - cliccare su `com.abdu.SafariAR`;
  - cliccare su `files`;
  - eliminare `LogFiles.txt`.

Dopo aver effettuato i sopraccitati controlli, avviare lo script di test e attenderne la fine. Per visualizzare il report cliccare sulla tab `Run` e successivamente su `View Report`. Nella stessa cartella dello script sarà creato il file delle interazioni che definirà le interazioni effettuate per ogni path eseguito. A questo punto è fondamentale estrarre il file di log dallo smartphone. Per farlo è necessario utilizzare il programma [Analisi Log](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/analisi%20log/Analisi%20Log.jar), avendo cura di non chiudere il dispositivo emulato, effettuando i seguenti step:
1. avviare `Analisi Log`;
2. cliccare sul bottone `Importa`;
3. inserire come nome package `com.abdu.SafariAR`;
4. inserire come nome del file di log un qualsiasi nome che non contenga spazi e\o simboli;
5. cliccare sul bottone `Importa`.

Se tutto sarà andato a buon fine, sarà presente un messaggio di avvenuto caricamento, il file di log sarà stato cancellato dal dispositivo emulato e sarà presente sul Desktop con il nome scelto. A questo punto sarà possibile caricare gli script al fine di valutarne la copertura. Per farlo è necessario cliccare sul bottone `Carica Script` e inserire i file, contenenti le sonde, da valutare (`Autofocus`, `AnimalSpawn`, `AnimalsController`). Dopo averli caricati sarà necessario cliccare su `Analizza`. Fatto ciò si aprirà una finestra che mostra quali rami sono stati eseguiti e quante volte e la percentuale di copertura dello script (rami eseguiti / rami totali). Nel caso in cui la percentuale di copertura fosse inferiore al 100% per ogni script sarà possibile cliccare il tasto `Rami non eseguiti`, che mostrerà quali rami del codice non sono stati eseguiti. Quanto descritto può essere effettuato per ognuno degli script di test (`testAllTransionsCoverageNew.air`, `testAllTransionsCoverageEstesoNew.air`, `testAllStateCoverageNew.air`, `testAllOneLoopPathsCoverageNew.air`). Si noti che è possibile bypassare l'esecuzione dei test scaricando i log e i file delle interazioni da [qui](https://github.com/danilobevilacqua/TesiMagistrale/tree/main/Safari%20Animal%20AR/applicazione). Sarà possibile analizzare il file di log utilizzando il bottone `Carica` del programma `Analisi Log` che permette di caricare il file non dal dispositivo emulato ma dal PC.
 
#### Test mutanti
I mutanti sono delle versioni dell'applicazione a cui sono stati iniettati degli errori. Nella fattispecie dell'applicazione in esame, ne sono stati creati due. 

##### Mutante 1
Il mutante 1 prevede l'iniezione di un errore relativo al movimento dell'animale. In altre parole spostando il pad a destra l'animale si muove verso sinistra e viceversa. Tale errore è stato ottenuto modificando lo script AnimalsController. Quanto sarà detto potrà essere bypassato se si scarica l'[apk](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/mutante%201/safariErroreMovimentoMutante1.apk) del mutante e lo si installa, mediante drag and drop, sul dispositivo emulato. Per creare il mutante 1 è necessario:
* avviare UnityHub aprendo il progetto Safari Animal AR;
* all'interno di Unity, aprire la cartella `_Script` del progetto;
* aprire il file `AnimalsController.cs`;
* modificare l'istruzione alla riga 41, mettendo il simbolo meno (-) subito dopo il simbolo di uguale;
* salvare lo script;
* generare l'apk come mostrato in precedenza.

In alternativa anziché modificare il file lo si può sostituire con quello già modificato, scaricabile da [qui](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/mutante%201/AnimalsController.cs). Una volta ottenuto il mutante bisognerà avviare AirTestIDE, collegare il dispositivo emulato e startare il file di test del criterio di copertura desiderato (rispettando quanto detto nella sezione precedente). Se lo script di test sarà in grado di rilevare l'errore, AirTestIDE mostrerà quale asserzione è fallita. In particolare, generando il report dello script, si vedrà chiaramente quale asserzioni sono state soddisfatte e quale ha causato l'errore. A riprova di quanto detto, anche il file delle interazioni (presente nella medesima cartella dello script avviato) non presenterà la stringa finale "Test concluso senza errori". Più precisamente, all'interno di tale file saranno presenti tutti i paths, con le relative interazioni, effettuati fino a prima del malfunzionamento. La sequenza di interazioni causa dell'errore sarà relativa esclusivamente all'ultimo path eseguito.


##### Mutante 2
Il mutante 2 prevede l'iniezione di un errore relativo alla comparsa di più animali sulla scena. In particolare l'unico animale ad essere solo sulla scena sarà la zebra. Infatti, cliccando uno dei bottoni relativo a un qualsiasi altro animale esso sarà compresente con la zebra nella scena AR. Quanto sarà detto potrà essere bypassato se si scarica l'[apk](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/mutante%202/safariErroreAnimaliMutante2.apk) del mutante e lo si installa, mediante drag and drop, sul dispositivo emulato. Per creare il mutante 2 è necessario:
* avviare UnityHub aprendo il progetto Safari Animal AR;
* all'interno di Unity, aprire la cartella `_Script` del progetto;
* aprire il file `AnimalSpawn.cs`;
* modificare l'istruzione alla riga 77, inserendo l'istruzione `models[0].SetActive(true)`;
* salvare lo script;
* generare l'apk come mostrato in precedenza.   

In alternativa anziché modificare il file lo si può sostituire con quello già modificato, scaricabile da [qui](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Safari%20Animal%20AR/mutante%202/AnimalSpawn.cs). Per eseguire gli script di test sul mutante, basterà seguire quanto nella sezione relativa al mutante 1.



### Point AR
Di seguito saranno definiti i passi da seguire per importare l'applicazione Point AR in locale. Si noti che quanto sarà spiegato nella sezione Import Applicazione potrà essere bypassato scaricando direttamente il [file apk](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/applicazione/pointAR.apk), al fine di installarlo sul dispositivo virtuale mediante drag and drop. 

#### Import Applicazione
E' necessario scaricare, in formato zip, l'intero repository [Point AR](https://github.com/abdullahibneat/PointAR) e seguire le istruzioni della sezione `How to use` del medesimo repository. Diversamente dall'applicazione precedente qui sono necessari due marker, che saranno identificati come [marker 1](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/marker1.png) e [marker 2](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/marker2.png). Al progetto dovrà essere aggiunto Poco-SDK, allo stesso modo del caso precedente, e bisognerà aggiungere il file `SondaManager` al fine di inserire le sonde all'interno degli script presenti nel progetto. Alternativamente gli scipt possono essere sostituiti con quelli già contenenti le sonde, scaricabili da [qui](https://github.com/danilobevilacqua/TesiMagistrale/tree/main/Point%20AR/script%20con%20sonde).

#### Test
Per eseguire i test saranno effettuati gli stessi passi mostrati in precedenza. Tuttavia, diversamente dal caso precedente, per ogni criterio di copertura si hanno due\tre script, ciascuno relativo al caso in cui sia inquadrato il marker 1, il marker 2 o nessuno dei due. Quindi prima di avviare lo script di test verificare che sia presente il marker corretto nella stanza virtuale (preferibilmente posizionarlo sul muro); Il file di log dovrà essere importato dal dispositivo virtuale, mediante il programma `Analisi Log`, solo quando saranno stati eseguiti tutti gli script relativi al criterio di copertura scelto. L’unica differenza rispetto al caso precedente consta nel diverso nome del package da inserire (`com.abdu.PointAR`). Per quanto riguarda i file delle interazioni, anche se relativi allo stesso criterio, si troveranno nelle rispettive cartelle degli script di test. Si noti che gli script di test [testAllTransitionsCoverageMarker1](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/testAllTransitionsCoverageMarker1.air/testAllTransitionsCoverageMarker1.py) e [testAllOneLoopPathsCoverageAttesa](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/testAllOneLoopPathsCoverageAttesa.air/testAllOneLoopPathsCoverageAttesa.py) presentano delle istruzioni commentate. Se tali istruzioni si decommentassero si otterrebbe la versione estesa (eseguendo anche gli altri script relativi allo stesso criterio) del criterio selezionato.  Come nel caso precedente è possibile bypassare l'esecuzione dei test scaricando i log e i file delle interazioni da [qui](https://github.com/danilobevilacqua/TesiMagistrale/tree/main/Point%20AR/applicazione).

#### Test mutanti
Così come nel caso precedente, anche per questa applicazione sono stati creati due mutanti. 

##### Mutante 1
Il mutante 1 prevede l'iniezione di un errore relativo all'apertura del menù a tendina. In altre parole cliccando sul menù a tendina per selezionare la lingua, esso non sarà in grado di aprirsi per mostrare tutte le lingue disponibili per la traduzione. Quanto sarà detto potrà essere bypassato se si scarica l'[apk](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/mutante%201/pointARErroreDropdown.apk) del mutante e lo si installa, mediante drag and drop, sul dispositivo emulato. Per creare il mutante 1 è necessario:
* avviare UnityHub aprendo il progetto Point AR;
* all'interno di Unity, navigare la gerarchia di GameObject cliccando su `Canvas`, poi su `LanguageSelectionGroup` e infine su `Dropdown`;
* togliere la spunta dalla voce `Dropdown (Script)` come mostrato in [figura](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/mutante%201/spunta%20dropdown.PNG); 
* generare l'apk come mostrato in precedenza.

Per eseguire gli script di test sul mutante, basterà seguire quanto descritto precedentemente.

##### Mutante 2
Il mutante 2 prevede l'iniezione di un errore relativo alla traduzione del marker 1 in lituano. In altre parole selezionando la lingua lituana, il marker 1 non sarà tradotto nella lingua selezionata ma in italiano. Quanto sarà detto potrà essere bypassato se si scarica l'[apk](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/mutante%202/pointARErroreLituanoItaliano.apk) del mutante e lo si installa, mediante drag and drop, sul dispositivo emulato. Per creare il mutante 2 è necessario:
* avviare UnityHub aprendo il progetto Point AR;
* all'interno di Unity, aprire la cartella `_Script` del progetto;
* aprire il file `translate.cs`;
* modificare le istruzioni alla riga 71 e 72, invertendo i valori di `true` e `false`;
* salvare lo script;
* generare l'apk come mostrato in precedenza. 

In alternativa anziché modificare il file lo si può sostituire con quello già modificato, scaricabile da [qui](https://github.com/danilobevilacqua/TesiMagistrale/blob/main/Point%20AR/mutante%202/translate.cs). Per eseguire gli script di test sul mutante, basterà seguire quanto descritto precedentemente.
