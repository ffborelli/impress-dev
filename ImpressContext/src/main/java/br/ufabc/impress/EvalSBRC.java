//package br.ufabc.impress;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class EvalSBRC {
//
//	private static int exp = 152;
//
//	public static void main(String[] args) {
//
//		Template arrExp[] = new Template[exp];
//
//		String linha = null;
//		String[] campos = null;
//		try {
//			BufferedReader br = new BufferedReader(
//					new FileReader("evalSDP.txt"));
//			while (br.ready()) {
//				linha = br.readLine();
//				System.out.println(linha);
//
//				campos = linha.split(";");
//
//				// 0 --> nome do experimento; 1 --> tempo inicial; 2 --> tempo
//				// final ; 3 --> duração ; 4 --> duração ; 5 -> módulo ;
//
//				String[] strIndex = campos[0].split(" ");
//
//				int index = Integer.parseInt(strIndex[1]) - 1;
//
//				if (arrExp[index] == null) {
//					arrExp[index] = new Template();
//				}
//
//				if (campos[5].equalsIgnoreCase("START TIME")) {
//					
//					arrExp[index].inicio = Long.parseLong(campos[1]);
//				} else if (campos[5].equalsIgnoreCase("FINISH TIME")) {
//					arrExp[index].inicio = Long.parseLong(campos[1]);
//					arrExp[index].fim = Long.parseLong(campos[2]);
//					arrExp[index].duration = arrExp[index].fim - arrExp[index].inicio; 
//				}
//
//				else if (campos[5].equalsIgnoreCase("fusion temperature")) {
//					arrExp[index].mens_fusao_temp += 1;
//					arrExp[index].media_fusao_temp += Double
//							.parseDouble(campos[3]);
//				}
//
//				else if (campos[5].equalsIgnoreCase("rule temperature")) {
//					arrExp[index].mens_regras_temp += 1;
//					arrExp[index].media_regras_temp += Double
//							.parseDouble(campos[3]);
//				}
//
//			}
//			br.close();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//		
//		try {
//			// O parametro é que indica se deve sobrescrever ou continua
//			// no
//			// arquivo.
//			FileWriter fw = new FileWriter("results.txt", true);
//			BufferedWriter conexao = new BufferedWriter(fw);
//			conexao.write("Experimento "  
//					+ " ; Dur "  
//					+ " ; Msg Fusão " 
//					+ " ; Msg Regras "
//					+ " ; Tm regras "  
//					);
//			conexao.newLine();
//			conexao.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < arrExp.length; i++) {
//
//			if (arrExp[i] != null) {
//
//				//arrExp[i].duration = arrExp[i].fim - arrExp[i].inicio;
//				arrExp[i].media_regras_temp = arrExp[i].media_regras_temp
//						/ arrExp[i].mens_regras_temp;
//				arrExp[i].media_fusao_temp = arrExp[i].media_fusao_temp
//						/ arrExp[i].mens_fusao_temp;
//
//				try {
//					// O parametro é que indica se deve sobrescrever ou continua
//					// no
//					// arquivo.
//					FileWriter fw = new FileWriter("results.txt", true);
//					BufferedWriter conexao = new BufferedWriter(fw);
//					conexao.write( (i + 1) 
//							+ " ; " + (int)arrExp[i].duration 
//							+ " ; " + arrExp[i].mens_fusao_temp
//							+ " ; " + arrExp[i].mens_regras_temp
//							+ " ; " + arrExp[i].media_regras_temp 
//							);
//					conexao.newLine();
//					conexao.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
//
//	static class Template {
//
//		double media_fusao_temp = 0;
//		double media_regras_temp = 0;
//		double mens_fusao_temp = 0;
//		double mens_regras_temp = 0;
//
//		long inicio;
//		long fim;
//		long duration;
//
//	}
//
//}
