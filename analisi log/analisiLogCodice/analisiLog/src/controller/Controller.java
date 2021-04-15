package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import entity.LogFile;
import entity.Script;

public class Controller {

	private static ArrayList<Script> listaScript = new ArrayList<Script>();
	private static LogFile log;
	private static String userPath = System.getProperty("user.home");

	public static void importaLog(String nomePackage, String nomeLog) throws Exception {
		
		if(!verificaPackage(nomePackage)) {
			throw new Exception("package non trovato");
		}
		
		String cmdRenameLog = "cmd /c  cd "+userPath+"\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows && adb shell mv /storage/self/primary/Android/data/"+nomePackage+"/files/LogFile.txt /storage/self/primary/Android/data/"+nomePackage+"/files/"+nomeLog+".txt";
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(cmdRenameLog);
		pr.waitFor();
		String cmdGetLog = "cmd /c  cd "+userPath+"\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows && adb pull /storage/self/primary/Android/data/"+nomePackage+"/files/"+nomeLog+".txt "+ userPath +"\\Desktop";
		pr = run.exec(cmdGetLog);
		pr.waitFor();
		String cmdRemoveLog = "cmd /c  cd "+userPath+"\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows && adb shell rm -f /storage/self/primary/Android/data/"+nomePackage+"/files/"+nomeLog+".txt";
		pr = run.exec(cmdRemoveLog);
		pr.waitFor();

		log = new LogFile(userPath+"\\Desktop\\"+nomeLog+".txt");
	}

	private static boolean verificaPackage(String nomePackage) throws IOException, InterruptedException {
		boolean flag = false;

		String cmdGetLog = "cmd /c  cd "+userPath+"\\Desktop\\AirtestIDE\\airtest\\core\\android\\static\\adb\\windows && adb shell \"pm list packages | grep "+nomePackage+"\"";
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(cmdGetLog);
		pr.waitFor();
		BufferedReader reader = new BufferedReader( new InputStreamReader( pr.getInputStream() ) );

		String line = "";
		while ((line = reader.readLine())!= null) {
			if(line.equals("package:"+nomePackage)) {
				flag=true;
			}
		}
		return flag;
	}

	public static void caricaLog(File f) throws FileNotFoundException {
		if(f != null) {
			log = new LogFile(f.getAbsolutePath());
		}
	}

	public static boolean presenzaLogFile() {
		boolean flag = false;
		if(log != null) {
			flag = true;
		}
		return flag;
	}

	public static void aggiungiScript(File f) throws FileNotFoundException {
		if (f != null) {
			listaScript.add(new Script(f.getAbsolutePath(),f.getName()));
		}
	}

	public static List<String> getNomiScript() {
		List<String> listaNomiScript = new ArrayList<String>();
		for(Script s : listaScript) {
			listaNomiScript.add(s.getNome());
		}
		return listaNomiScript;
	}

	public static void rimuoviScript(int index) {
		listaScript.remove(index);
	}

	public static String numeroEsecuzioniPerRamo(){

		String stringaEsecuzioni = "";
		ArrayList<String> setOrdinato = new ArrayList<String>(log.getSetLog());
		Collections.sort(setOrdinato);
		if (log != null) {
			for(String ls : setOrdinato) {
				int count = 0;
				for(String rl : log.getRigheLog()) {
					if(rl.equals(ls)) {
						count++;
					}
				}
				stringaEsecuzioni += ""+ls+" eseguito: "+count;
				if (count>1) {
					stringaEsecuzioni += " volte\n";
				}
				else {
					stringaEsecuzioni += " volta\n";
				}
			}
		}		

		return stringaEsecuzioni;
	}

	public static HashMap<String,ArrayList<String>> coperturaCodice() throws FileNotFoundException {

		HashMap<String,ArrayList<String>> mappaStatistiche = new  HashMap<String,ArrayList<String>>();
		if (log != null && listaScript.size()>0) {
			ArrayList<Script> listaScriptNew = new ArrayList<Script>();
			for(Script s : listaScript) {
				Script newScript = new Script(s.getPath(),s.getNome());
				newScript.calcolaStatistiche(log);
				listaScriptNew.add(newScript);				
				mappaStatistiche.put(newScript.getNome()+" "+newScript.getPercentualeCopertura()+"%", newScript.getRamiNonCoperti());
			}
			listaScript = listaScriptNew;
		}
		return mappaStatistiche;

	}

}
