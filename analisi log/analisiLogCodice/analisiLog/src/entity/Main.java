package entity;

import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		try {
			String cmdGetLog = "cmd /c  "+System.getProperty("user.home")+"\\Desktop\\logAllOneLoopPathsCoverage.txt";
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmdGetLog);
			pr.waitFor();
			
			System.out.println(System.getProperty("user.home"));
			
			/*
			String cmdRemoveLog = "cmd /c  cd C:\\Users\\Danilo\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows && adb shell rm -f /storage/self/primary/Android/data/com.abdu.SafariAR/files/LogSafari.txt";
			pr = run.exec(cmdRemoveLog);
			pr.waitFor();
			
			Script script1 = new Script("C:\\Users\\Danilo\\Desktop\\SafariAnimalsAR-master\\Assets\\_Scripts\\AnimalSpawn.cs");
			Script script2 = new Script("C:\\Users\\Danilo\\Desktop\\SafariAnimalsAR-master\\Assets\\_Scripts\\AutoFocus.cs");
			Script script3 = new Script("C:\\Users\\Danilo\\Desktop\\SafariAnimalsAR-master\\Assets\\_Scripts\\AnimalsController.cs");

			System.out.println("ANIMAL SPAWN");
			script1.stampaRamiScript();
			System.out.println("\nAUTOFOCUS");
			script2.stampaRamiScript();
			System.out.println("\nANIMAL CONTROLLER");
			script3.stampaRamiScript();

			ArrayList<Script> listaScript = new ArrayList<Script>();
			listaScript.add(script1);
			listaScript.add(script2);
			listaScript.add(script3);

			System.out.println(script2.getNome());

			LogFile lf = new LogFile("C:\\Users\\Danilo\\Desktop\\LogSafari.txt");

			/*
			System.out.println("\nLOG FILE");
			lf.stampaLog();		
			


			System.out.println("\nSET LOG");
			lf.stampaSetLog();


			HashMap<String,Integer> mappaEsecuzioni = numeroEsecuzioniPerRamo(lf);
			System.out.println("\nNUMERO ESECUZIONI SET LOG");
			for(String s : mappaEsecuzioni.keySet()) {
				System.out.println(s+": "+mappaEsecuzioni.get(s));
			}

			ArrayList<StatisticaCopertura> listaStatistiche = coperturaCodice(listaScript, lf);
			System.out.println("\nCOPERTURA");
			for(StatisticaCopertura sc : listaStatistiche) {
				sc.stampaStatistiche();
			}

 */
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static HashMap<String,Integer> numeroEsecuzioniPerRamo(LogFile lf){

		HashMap<String,Integer> mappaEsecuzioni = new HashMap<String,Integer>();

		for(String log : lf.getSetLog()) {
			int count = 0;
			for(String l : lf.getRigheLog()) {
				if(l.equals(log)) {
					count++;
				}
			}
			mappaEsecuzioni.put(log, count);
		}

		return mappaEsecuzioni;
	}

}
