package br.ufabc.impress.file;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

public class File {

	public void write(String fileName, String m) {

		try {
			// O parametro é que indica se deve sobrescrever ou continua no arquivo.
	        String time = new Timestamp(System.currentTimeMillis()).toString();
	        String[] msg = m.split(";");
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter conexao = new BufferedWriter(fw);
			String line = null;
			for(int i = 0; i < msg.length; i++){
				line = line + "\"" + msg[i] + "\"";
				
			}
			line = line +  "\"" + time + "\"";
			conexao.write(line);
			conexao.newLine();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}