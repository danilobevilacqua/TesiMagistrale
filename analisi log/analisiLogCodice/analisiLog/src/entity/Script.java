package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Script {

	private String path;
	private ArrayList<String> ramiScript;
	private String nome;
	private Double percentualeCopertura;
	private ArrayList<String> ramiCoperti;
	private ArrayList<String> ramiNonCoperti;

	public Script(String path, String nome) throws FileNotFoundException {
		super();
		this.path = path;
		this.percentualeCopertura = 0.0;
		this.ramiScript = new ArrayList<String>();
		this.ramiCoperti = new ArrayList<String>();
		this.ramiNonCoperti = new ArrayList<String>();

		File f = new File(path);		
		Scanner scanner = new Scanner(f);
		while (scanner.hasNextLine()) {
			String data = scanner.nextLine();			
			recuperaSonda(data);
		}
		scanner.close();
		setNome(nome);

	}

	public String getPath() {
		return path;
	}

	public ArrayList<String> getRamiScript() {
		return ramiScript;
	}


	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {

		this.nome = nome.split("\\.")[0];

	}
	

	public Double getPercentualeCopertura() {
		return percentualeCopertura;
	}

	public ArrayList<String> getRamiCoperti() {
		return ramiCoperti;
	}

	public ArrayList<String> getRamiNonCoperti() {
		return ramiNonCoperti;
	}

	private void recuperaSonda(String data) {

		if(data.contains("SondaManager")) {
			data = data.trim();
			String [] dataSplit = data.split("\"");
			this.ramiScript.add(dataSplit[1]);
		}

	}

	public void calcolaStatistiche(LogFile lf) {
		
		int count = 0;
		for(String ramo : this.getRamiScript()) {
			if(lf.getSetLog().contains(ramo)) {
				count++;
				this.ramiCoperti.add(ramo);
			}
			else {
				this.ramiNonCoperti.add(ramo);
			}
		}
		double percentuale = 0.0;
		if(this.getRamiScript().size()>0) {
			percentuale = Double.valueOf(count)*100/Double.valueOf(this.getRamiScript().size());
		}
		
		this.percentualeCopertura = percentuale;
	}

	public void stampaRamiScript() {
		for(String s : this.ramiScript) {
			System.out.println(s);
		}
	}

	public void stampaStatistiche() {
		System.out.println(this.nome +": " + this.percentualeCopertura+"%");
		if(!this.ramiNonCoperti.isEmpty()) {
			System.out.println("	Rami Non Coperti:");
			for(String s : this.ramiNonCoperti) {
				System.out.println("		"+s);
			}
		}
	}


}
