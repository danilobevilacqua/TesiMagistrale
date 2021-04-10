# -*- encoding=utf8 -*-
__author__ = "Danilo"

from airtest.core.api import *
auto_setup(__file__)
from poco.drivers.unity3d import UnityPoco
poco = UnityPoco()

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

def verificaScenaAr():
    
    global bottone;
    global linguaSelezionata;
    
    scenaAR = poco("LiftAnimation").children();
    bottone = poco(type = 'Button');
    comparsaScenaAr = False;
    
    if(len(scenaAR) > 1 and len(bottone) == 1 and bottone.attr('name') == "Settings"):
        if(linguaSelezionata != "English"):
            markerTradotto = poco("LIFT - "+linguaSelezionata);
            comparsaScenaAr = markerTradotto.exists();
        else:
            comparsaScenaAr = True
    
    assert_equal(comparsaScenaAr,True,"Scena AR comparsa");
    
    
poco = None;    
bottone = None;
listaLingue = [];
linguaSelezionata = None;

f = open("AllStatesCoverageInterazioniMarker1.txt","w+");

f.write("Unico paths All States Coverage Marker 1 \n");

avviaApp()
verificaSchermataSelezioneLingua("avvio")
bottone.click();
f.write("cliccato bottone: "+ bottone.attr('name')+"\n");
verificaScenaAr()
chiudiApp()
f.write("Test concluso senza errori \n");