# -*- encoding=utf8 -*-
__author__ = "Danilo"
from airtest.core.api import *
auto_setup(__file__)
from poco.drivers.unity3d import UnityPoco

def avviaApp():
    
    global poco;
    
    os.system('cmd /c "cd '+os.environ['USERPROFILE']+'\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows & adb shell am start com.abdu.PointAR/com.unity3d.player.UnityPlayerActivity"')
    time.sleep(15)
    inizializzazione()
    
def chiudiApp():
    keyevent("KEYCODE_APP_SWITCH")
    os.system('cmd /c "cd '+os.environ['USERPROFILE']+'\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows & adb shell am force-stop com.abdu.PointAR"')
	os.system('cmd /c "cd '+os.environ['USERPROFILE']+'\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows & adb shell am kill com.abdu.PointAR"')
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


    
poco = None;    
bottone = None;
listaLingue = [];
linguaSelezionata = None;

f = open("AllTransitionsCoverageInterazioniMarker1.txt","w+");

f.write("Unico paths All Transitions Coverage Marker 1 \n");

avviaApp();
verificaSchermataSelezioneLingua("avvio");
apriMenuTendina(); 

for i in range(len(listaLingue)):    
    if(i == len(listaLingue)-1):
        scorriMenuTendina();
    selezionaLingua(listaLingue[i].attr('name'));
    verificaSchermataSelezioneLingua("avvio");
    if(i != len(listaLingue)-1):
        apriMenuTendina();
    
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


"""
avviaApp();
verificaSchermataSelezioneLingua("avvio");
apriMenuTendina();
for i in range(1,-1,-1):    
    if(i == len(listaLingue)-1):
        scorriMenuTendina();
    selezionaLingua(listaLingue[i].attr('name'));
    verificaSchermataSelezioneLingua("avvio");
    if(i != 0):
        apriMenuTendina();

keyevent("KEYCODE_APP_SWITCH")
keyevent("KEYCODE_APP_SWITCH")
chiudiApp()
"""