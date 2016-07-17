package br.ufabc.impress.tracker;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.*;

public class TrackerGabriela {

	public static int[] FuncRecursiva(String elemento, int sensor, int maior_fusao_v, int maior_fusao_r) {
		
		int idvirtual_sensor = sensor;
		String lista2real = "0";
		String lista3virtual = "0";
		int fusao = 0;

		// System.out.println("XXXXXXX: " + elemento);

		Statement stm = null;
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager
					.getConnection("jdbc:postgresql://localhost/impress_recife_test?user=postgres&password=postgres");
			stm = con.createStatement();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		if (elemento == null) {

			// System.out.println("------------------------------");
			// System.out.println("LIGAÇÃO: SENSOR X FUSÃO");
			// System.out.println("------------------------------");

			int[] nro = {0,0};
			return nro;

		} else {

			try {

				ResultSet rs = stm
						.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
								+ elemento + "'))");

				ArrayList<String> lista5 = new ArrayList<String>();

				while (rs.next()) {
					lista5.add(rs.getString("dependence_fusion_log_fk"));
				}

				ResultSet rs4 = stm
						.executeQuery("select id_fusion_fk, id_fusion_log from fusion_log where id_fusion_log in (select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
								+ elemento + "')))");

				ArrayList<String> lista2 = new ArrayList<String>();
				ArrayList<String> lista3 = new ArrayList<String>();

				while (rs4.next()) {
					lista2.add(rs4.getString("id_fusion_fk"));
					lista3.add(rs4.getString("id_fusion_log"));
				}

				ResultSet rs99 = stm
						.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
								+ elemento + "'))");

				ArrayList<String> lista99 = new ArrayList<String>();

				String aux;

				while (rs99.next()) {
					aux = rs99.getString("dependence_fusion_log_fk");
					if (aux != null) {
						lista99.add(rs99.getString("dependence_fusion_log_fk"));

					}
				}

				FileWriter arquivo = new FileWriter(new File("teste.txt"), true);
				PrintWriter gravarArq = new PrintWriter(arquivo);

				int j;
				int m = lista2.size();

				for (j = 0; j < m; j++) {
					//gravarArq.println("______________________________ ");
					//gravarArq.println("ID REAL DA FUSÃO: " + lista2.get(j));
					//gravarArq.println("ID VIRTUAL DA FUSÃO:  " + lista3.get(j));

					lista2real =  lista2.get(j);
					lista3virtual =  lista3.get(j);
					
					ResultSet rs13 = stm
							.executeQuery("select id_resource, dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '" + elemento + "'))");

					ArrayList<String> lista13 = new ArrayList<String>();
					ArrayList<String> lista14 = new ArrayList<String>();

					while (rs13.next()) {

						lista13.add(rs13.getString("dependence_fusion_log_fk"));
						lista14.add(rs13.getString("id_resource"));

					}

					int d = 0;
					int tttam = lista13.size();

					for (d = 0; d < tttam; d++) {
						if (lista13.get(d) == null) {
							//System.out.println("LIGAÇÃO COM SENSOR:  " + lista14.get(d));
							
							idvirtual_sensor++;
							
							gravarArq.print("S" + idvirtual_sensor + "*" + lista14.get(d) + ":" + "F" + lista3virtual + "*" + lista2real + ";");

							fusao = Integer.parseInt(lista3virtual);
							if(fusao > maior_fusao_v){
								maior_fusao_v = fusao;
								maior_fusao_r = Integer.parseInt(lista2real);;
							}
							
						}
						if (lista13.get(d) != null) {
							//System.out.println("LIGAÇÃO COM OUTRA FUSÃO:  " + lista13.get(d));
							gravarArq.print("F" + lista13.get(d) + "*" + lista2real + ":" + "F" + lista3virtual + "*" + lista2real + ";");

							fusao = Integer.parseInt(lista13.get(d));
							if(fusao > maior_fusao_v){
								maior_fusao_v = fusao;
								maior_fusao_r = Integer.parseInt(lista2real);;
							}
							
						}
					}

				}

				int i;
				int n = lista5.size();

				// System.out.println("lista 5" + lista5);
				for (i = 0; i < n; i++) {

					// System.out.println("lista 5 (i) " + lista5.get(i));

					if (lista5.get(i) == null) {

						// System.out.println("RESOURCE elemento " + elemento);

						ResultSet rs1 = stm
								.executeQuery("select id_resource, dependence_fusion_log_fk from resource where id_resource in(select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '" + elemento + "')) ");

						ArrayList<String> lista6 = new ArrayList<String>();
						ArrayList<String> lista7 = new ArrayList<String>();
						// ArrayList<String> lista8 = new ArrayList<String>();

						while (rs1.next()) {
							lista6.add(rs1.getString("id_resource"));
							lista7.add(rs1
									.getString("dependence_fusion_log_fk"));
						}

						ResultSet rs8 = stm
								.executeQuery("select id_fusion_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
										+ elemento + "' ");

						// while (rs8.next()) {
						// System.out.print("ID VIRTUAL DA FUSÃO x SENSOR: ");
						// System.out.println(rs8.getString("id_fusion_log_fk"));
						// }

						// System.out.println("lista 6 inteira " + lista6);
						// System.out.println("lista 7 inteira " + lista7);

						int b = 0;
						int tamanho = lista6.size();

						// for (b = 0; b < tamanho; b++) {
						// if (lista7.get(b) == null) {
						// System.out.println("ID REAL DO SENDOR: " +
						// lista6.get(b));
						//
						// }
						// }

						// System.out.println("RESOURCE " +
						// rs1.getString("id_resource"));

					}

					int[] retorno_rec = FuncRecursiva(lista5.get(i), idvirtual_sensor, maior_fusao_v, maior_fusao_r);
					if(retorno_rec[0] > maior_fusao_v){
						maior_fusao_v = retorno_rec[0];
						maior_fusao_r = retorno_rec[1];
					}
				}
				gravarArq.close();
				arquivo.close();

				try {
					BufferedReader leitor = new BufferedReader(new FileReader("teste.txt"));
					while(leitor.ready()){
						String linha = leitor.readLine();
						System.out.println(linha);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			int[] valores = new int[] {maior_fusao_v, maior_fusao_r}; 
			return valores;

		}

	}

	public static void main(String[] args) {

		int idvirtual_regra = 1;
		int idvirtual_sensor = 0;
		int idvirtual_fusao = 0;
		int idvirtual_atuador = 0;
		String copyrs2real = "0";
		String copyrs2virtual = "0";
		String lista2real = "0";
		String lista3virtual = "0";
		int maior_fusao_r = 0;
		int maior_fusao_v = 0;
		int fusao = 0;
		int idreal_regra = 0;
		
		try {
			FileWriter arquivo = new FileWriter(new File("teste.txt"), true);
			PrintWriter gravarArq = new PrintWriter(arquivo);

			Class.forName("org.postgresql.Driver");
			Connection con;
			con = DriverManager
					.getConnection("jdbc:postgresql://localhost/impress_recife_test?user=postgres&password=postgres");
			Statement stm = con.createStatement();

			ResultSet rs1 = stm
					.executeQuery("select id_resource_fk, id_rule_fk from rule_action_log where creation_date = '14/10/29 13:45:54'");
			//gravarArq.println(" ");
			//gravarArq.println("------------------------------");
			//gravarArq.println("LIGAÇÃO: ATUADOR X REGRA");
			//gravarArq.println("------------------------------");
			while (rs1.next()) {
				//gravarArq.println("ID REAL DO ATUADOR: ");
				//gravarArq.println(rs1.getString("id_resource_fk"));
				//gravarArq.println("ID REAL DA REGRA: ");
				//gravarArq.println(rs1.getString("id_rule_fk"));
				
				idvirtual_atuador++;
				
				//
				// primeira gravacao
				//
				idreal_regra = Integer.parseInt(rs1.getString("id_rule_fk"));
				gravarArq.print("R" + idvirtual_regra + "*" + (rs1.getString("id_rule_fk")) + ":" + "A" + idvirtual_atuador + "*" + (rs1.getString("id_resource_fk")) + ";");
				
			}

			ResultSet rs2 = stm
					.executeQuery("select id_fusion_fk, id_fusion_log from fusion_log where id_fusion_log in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '14/10/29 13:45:54'))");

			//gravarArq.println(" ");
			//gravarArq.println("------------------------------");
			//gravarArq.println("LIGAÇÃO: FUSÃO X SENSOR");
			//gravarArq.println("------------------------------");

			while (rs2.next()) {

				//gravarArq.println("ID REAL DA FUSÃO: ");
				//gravarArq.println(rs2.getString("id_fusion_fk"));
				//gravarArq.println("ID VIRTUAL DA FUSÃO: ");
				//gravarArq.println(rs2.getString("id_fusion_log"));
				
				copyrs2real = rs2.getString("id_fusion_fk");
				copyrs2virtual = rs2.getString("id_fusion_log");
				
			}

			ResultSet rs10 = stm
					.executeQuery("select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '14/10/29 13:45:54')))");

			ArrayList<String> lista10 = new ArrayList<String>();

			while (rs10.next()) {

				lista10.add(rs10.getString("id_resource_fk"));

			}

			ResultSet rs11 = stm
					.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '14/10/29 13:45:54'))))");

			ArrayList<String> lista11 = new ArrayList<String>();

			while (rs11.next()) {

				lista11.add(rs11.getString("dependence_fusion_log_fk"));

			}

			ResultSet rs = stm
					.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '14/10/29 13:45:54'))))");

			ArrayList<String> lista = new ArrayList<String>();

			while (rs.next()) {
				lista.add(rs.getString("dependence_fusion_log_fk"));
			}

			int c = 0;
			int ttam = lista.size();

			for (c = 0; c < ttam; c++) {
				if (lista.get(c) == null) {
					//gravarArq.println("LIGAÇÃO COM SENSOR:  " + lista10.get(c));
					
					idvirtual_sensor++;
					
					gravarArq.print("S" + idvirtual_sensor + "*" + lista10.get(c) + ":" + "F" + copyrs2virtual + "*" + copyrs2real + ";");

					fusao = Integer.parseInt(copyrs2virtual);
					if(fusao > maior_fusao_v){
						maior_fusao_v = fusao;
						maior_fusao_r = Integer.parseInt(copyrs2real);
					}

				} else {
					//gravarArq.println("LIGAÇÃO COM OUTRA FUSÃO:  " + lista11.get(c));

					gravarArq.print("F" + lista11.get(c) + "*" + copyrs2real + ":" + "F" + copyrs2virtual + "*" + copyrs2real + ";");

					fusao = Integer.parseInt(lista11.get(c));
					if(fusao > maior_fusao_v){
						maior_fusao_v = fusao;
						maior_fusao_r = Integer.parseInt(copyrs2real);
					}
					
					fusao = Integer.parseInt(copyrs2virtual);
					if(fusao > maior_fusao_v){
						maior_fusao_v = fusao;
						maior_fusao_r = Integer.parseInt(copyrs2real);
					}
					
				}
			}

			ResultSet rs4 = stm
					.executeQuery("select id_fusion_fk, id_fusion_log from fusion_log where id_fusion_log in (select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '14/10/29 13:45:54')))))");

			ArrayList<String> lista2 = new ArrayList<String>();
			ArrayList<String> lista3 = new ArrayList<String>();

			while (rs4.next()) {
				lista2.add(rs4.getString("id_fusion_fk"));
				lista3.add(rs4.getString("id_fusion_log"));
			}

			int j;
			int m = lista.size();

			for (j = 0; j < m; j++) {
				if (lista.get(j) != null) {
					//gravarArq.println(" ");
					//gravarArq.println("______________________________ ");
					//gravarArq.println("ID REAL DA FUSÃO: " + lista2.get(j));
					//gravarArq.println("ID VIRTUAL DA FUSÃO:  " + lista3.get(j));
					
					lista2real = lista2.get(j);
					lista3virtual = lista3.get(j);

					ResultSet rs13 = stm
							.executeQuery("select id_resource, dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
									+ lista.get(j) + "'))");

					ArrayList<String> lista13 = new ArrayList<String>();
					ArrayList<String> lista14 = new ArrayList<String>();

					while (rs13.next()) {

						lista13.add(rs13.getString("dependence_fusion_log_fk"));
						lista14.add(rs13.getString("id_resource"));

					}

					int d = 0;
					int tttam = lista13.size();

					for (d = 0; d < tttam; d++) {
						if (lista13.get(d) == null) {
							//gravarArq.println("LIGAÇÃO COM SENSOR:  " + lista14.get(d));
							
							idvirtual_sensor++;
							gravarArq.print("S" + idvirtual_sensor + "*" + lista14.get(d) + ":" + "F" + lista3virtual + "*" + lista2real + ";");
									
							fusao = Integer.parseInt(lista3virtual);
							if(fusao > maior_fusao_v){
								maior_fusao_v = fusao;
								maior_fusao_r = Integer.parseInt(lista2real);
							}
							

						}
						if (lista13.get(d) != null) {
							//gravarArq.println("LIGAÇÃO COM OUTRA FUSÃO:  " + lista13.get(d));
							
							gravarArq.print("F" + lista13.get(d) + "*" + lista2real + ":" + "F" + lista3virtual + "*" + lista2real + ";");

							fusao = Integer.parseInt(lista13.get(d));
							if(fusao > maior_fusao_v){
								maior_fusao_v = fusao;
								maior_fusao_r = Integer.parseInt(lista2real);
							}
						}
					}

				}
			}
			int i;
			int n = lista.size();

			for (i = 0; i < n; i++) {

				if (lista.get(i) == null) {

					ResultSet rs9 = stm
							.executeQuery("select id_resource from resource where id_resource in(select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '14/10/29 13:45:54'))))");

					// while (rs9.next()) {
					// System.out.print("ID REAL DO SENSOR: ");
					// System.out.println(rs9.getString("id_resource"));
					// }

					ResultSet rs8 = stm
							.executeQuery("select id_fusion_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '14/10/29 13:45:54'))");

					// while (rs8.next()) {
					// System.out.print("ID VIRTUAL DA FUSÃO x SENSOR: ");
					// System.out.println(rs8.getString("id_fusion_log_fk"));
					// }

				}

				int[] retorno_rec = FuncRecursiva(lista.get(i), idvirtual_sensor, maior_fusao_v, maior_fusao_r);
				if(retorno_rec[0] > maior_fusao_v){
					maior_fusao_v = retorno_rec[0];
					maior_fusao_r = retorno_rec[1];
				}
				
				gravarArq.print("R" + idvirtual_regra + "*" + idreal_regra + ":" + "F" + maior_fusao_v + "*" + maior_fusao_r + ";");
				
			}

			
			
			gravarArq.close();
			arquivo.close();
			
			try {
				BufferedReader leitor = new BufferedReader(new FileReader("teste.txt"));
				while(leitor.ready()){
					String linha = leitor.readLine();
					System.out.println(linha);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}