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


def verificaAssenzaScenaAr():
    
    global bottone;
    global linguaSelezionata;
    
    scenaARMarker1 = poco("FallAnimation").children();
    scenaARMarker2 = poco("LiftAnimation").children();
    bottone = poco(type = 'Button');
    comparsaScenaAr = False;
    
    if((len(scenaARMarker1) > 1 or len(scenaARMarker2) > 1) and len(bottone) == 1 and bottone.attr('name') == "Settings"):
        comparsaScenaAr = True
    
    assert_equal(comparsaScenaAr,False,"Scena AR non comparsa");
    
poco = None;    
bottone = None;
listaLingue = [];
linguaSelezionata = None;

f = open("AllOneLoopPathsCoverageInterazioniAttesa.txt","w+");
f.write("Paths All One Loop Paths Coverage Attesa \n");


avviaApp();
verificaSchermataSelezioneLingua("avvio");
f.write("\nPATHS LUNGHEZZA 2 \n");
f.write("path 1\n");
#PATHS DI LUNGHEZZA DUE 4
apriMenuTendina();
for i in range(len(listaLingue)):    
    if(i == len(listaLingue)-1):
        scorriMenuTendina();
    print(listaLingue[i].attr('name'))
    selezionaLingua(listaLingue[i].attr('name'));
    verificaSchermataSelezioneLingua("avvio");
    chiudiApp();
    avviaApp();
    verificaSchermataSelezioneLingua("avvio")
    if(i < len(listaLingue)-1):
        f.write("\npath "+str(i+2)+"\n");
    if(i != len(listaLingue)-1):
        apriMenuTendina();
        

#PATHS DI LUNGHEZZA TRE 4 + 1
f.write("\nPATHS LUNGHEZZA 3 \n");
f.write("path 1\n");
apriMenuTendina();
for i in range(len(listaLingue)):    
    if(i == len(listaLingue)-1):
        scorriMenuTendina();
    print(listaLingue[i].attr('name'))
    selezionaLingua(listaLingue[i].attr('name'));
    verificaSchermataSelezioneLingua("avvio");
    bottone.click();
    f.write("cliccato bottone: "+ bottone.attr('name')+"\n");
    verificaAssenzaScenaAr();
    chiudiApp();
    avviaApp();
    verificaSchermataSelezioneLingua("avvio")
    f.write("\npath "+str(i+2)+"\n");
    if(i != len(listaLingue)-1):
        apriMenuTendina();

bottone.click();
f.write("cliccato bottone: "+ bottone.attr('name')+"\n");
verificaAssenzaScenaAr();
bottone.click();
f.write("cliccato bottone: "+ bottone.attr('name')+"\n");
verificaSchermataSelezioneLingua("appAvviata");
chiudiApp();
f.write("\nTest concluso senza errori \n");

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