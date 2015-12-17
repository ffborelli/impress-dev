package br.ufabc.impress.file;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class File {

	public void write(String fileName, String line) {

		try {
			// O parametro é que indica se deve sobrescrever ou continua no
			// arquivo.
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter conexao = new BufferedWriter(fw);
			conexao.write(line);
			conexao.newLine();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
