# Testing di applicazioni AR Mobile
Prima di descrivere i passi mediante i quali replicare i test condotti, si illustrano i file e le cartelle presenti all'interno del repository. All'inizio della gerarchia sono presenti due cartelle e un file:
* Analisi Log, file .jar il necessario per l'analisi dei file di log (come sarà mostrato di seguito);
* Safari Animal AR;
* Point AR.

Le due cartelle sono relative alle omonime applicazioni applicazioni (raggiungibili al link [Safari AR](https://github.com/abdullahibneat/SafariAnimalsAR) e [Point AR](https://github.com/abdullahibneat/PointAR)), che sono state utilizzate come casi di studio. All'interno della cartella Safari Animal AR è presente:
* la cartella applicazione contentiene l'apk e i file di log e delle interazioni per ciascuna tipologia di copertura (entrambi all'interno della rispettiva cartella);
* la cartella mutante 1 contiene l'apk dell'applicazione modificata (è stato iniettato l'errore di movimento, ovvero spostando il pad a destra l'animale si sposta a sinistra e viceversa), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella mutante 2 contiene l'apk dell'applicazione modificata (è stato iniettato l'errore di molteplice presenza di animali sulla scena, ossia scegliendo un qualsiasi animale che non sia la zebra esso non sarà solo sulla scena ma sarà compresente con la zebra), i file delle interazioni all'interno delle rispettive cartelle e lo script modificato causa dell'errore;
* la cartella script con sonde contiene gli script originali dell'applicazione a cui sono state aggiunte le sonde per la verifica della copertura del codice;
* le diverse tipologie di test condotti (testAllOneLoopPathsCoverageNew.air, testAllStateCoverageNew.air, testAllTransionsCoverageNew.air) e testAllTransionsCoverageEstesoNew.air);
* il marker.

Similmente all'interno della cartella




Al fine di replicare i test effettuati, di seguito sarà effettuata una descrizione dettagliata di quanto necessario.

## Installazione Tools
