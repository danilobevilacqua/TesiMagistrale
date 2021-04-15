package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class LogFile {

	private String path;
	private ArrayList<String> righeLog;
	private HashSet <String> setLog;

	public LogFile(String path) throws FileNotFoundException {
		super();
		this.path = path;
		this.righeLog = new ArrayList<String>();
		this.setLog = new HashSet<String>();

		File f = new File(path);

		Scanner scanner = new Scanner(f);
		while (scanner.hasNextLine()) {
			String data = scanner.nextLine();			
			this.righeLog.add(data);
			this.setLog.add(data);
		}
		scanner.close();

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ArrayList<String> getRigheLog() {
		return righeLog;
	}

	public void setRigheLog(ArrayList<String> righeLog) {
		this.righeLog = righeLog;
	}

	public HashSet<String> getSetLog() {
		return setLog;
	}

	public void setSetLog(HashSet<String> setLog) {
		this.setLog = setLog;
	}

	public void stampaLog() {

		for(String s : this.righeLog) {
			System.out.println(s);
		}

	}

	public void stampaSetLog() {

		for(String s : this.setLog) {
			System.out.println(s);
		}
	}
}
