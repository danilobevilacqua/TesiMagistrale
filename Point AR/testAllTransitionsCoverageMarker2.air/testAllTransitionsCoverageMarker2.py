# -*- encoding=utf8 -*-
__author__ = "Danilo"

from airtest.core.api import *

auto_setup(__file__)
from poco.drivers.unity3d import UnityPoco

def avviaApp():    
    os.system('cmd /c "cd '+os.environ['USERPROFILE']+'\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows & adb shell am start com.abdu.PointAR/com.unity3d.player.UnityPlayerActivity"')
    time.sleep(15)
    inizializzazione();
    
def chiudiApp():
    keyevent("KEYCODE_APP_SWITCH")
    keyevent("DEL")
    time.sleep(5)
            
def inizializzazione():
    
    global poco;
    global linguaSelezionata;
    
    poco = UnityPoco();
    linguaDefault = "English";
    linguaSelezionata = linguaDefault;
    
def verificaSchermataSelezioneLingua(tipoSchermata):
    
    global bottone;
    
    menuTendina = poco("LanguageSelectionGroup").children();
    bottone = poco(type = 'Button');
    presenzaSchermata = False;
    nomeBottone= None;
    
    if(tipoSchermata == "avvio"):
        nomeBottone = "StartButton";
    else:
        nomeBottone = "ResumeButton";
    
    if(len(menuTendina) > 0 and len(bottone) == 1 and bottone.attr('name') == nomeBottone): 
        presenzaSchermata = True;    
    
    assert_equal(presenzaSchermata,True,"schermata iniziale comparsa");
    
    
def apriMenuTendina():
    
    global listaLingue;
    
    seleziona = poco("Arrow").click();
    f.write("cliccato menu tendina \n");
    listaLingue = poco(type='Toggle');
    aperturaMenu = False;
    if(len(listaLingue) > 0):
        aperturaMenu = True;
    assert_equal(aperturaMenu,True,"menu a tendina aperto")
    
def scorriMenuTendina():
    poco("Handle").swipe([0.0044, 0.0749]);

def selezionaLingua(lingua):
    
    global linguaSelezionata;
    
    itemDaSelezionare = poco(lingua);
    itemSelezionato = itemDaSelezionare.children()[2];
    linguaSelezionata = itemSelezionato.get_text().capitalize();
    f.write("selezionata lingua: "+linguaSelezionata+"\n");
    itemSelezionato.click();


def verificaScenaAr():
    
    global bottone;
    global linguaSelezionata;
    
    scenaAR = poco("FallAnimation").children();
    bottone = poco(type = 'Button');
    comparsaScenaAr = False;
    
    if(len(scenaAR) > 1 and len(bottone) == 1 and bottone.attr('name') == "Settings"):
        if(linguaSelezionata != "English"):
            markerTradotto = poco("FALL - "+linguaSelezionata);
            comparsaScenaAr = markerTradotto.exists();
        else:
            comparsaScenaAr = True
    
    assert_equal(comparsaScenaAr,True,"Scena AR comparsa");
    
poco = None;    
bottone = None;
listaLingue = [];
linguaSelezionata = None;

f = open("AllTransitionsCoverageInterazioniMarker2.txt","w+");

f.write("Unico paths All Transitions Coverage Marker 2 \n");

avviaApp();
verificaSchermataSelezioneLingua("avvio");
    
bottone.click();
f.write("cliccato bottone: "+ bottone.attr('name')+"\n");
verificaScenaAr();
bottone.click();
f.write("cliccato bottone: "+ bottone.attr('name')+"\n");
verificaSchermataSelezioneLingua("AppAvviata");
bottone.click();
f.write("cliccato bottone: "+ bottone.attr('name')+"\n");
verificaScenaAr();
chiudiApp();
f.write("Test concluso senza errori \n");