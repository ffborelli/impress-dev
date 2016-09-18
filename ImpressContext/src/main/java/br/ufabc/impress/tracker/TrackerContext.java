package br.ufabc.impress.tracker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

import br.ufabc.impress.Param;

public class TrackerContext {

	private int index_db = 0;

	//private static String address_db = "jdbc:postgresql://localhost/checkTracker?user=postgres&password=postgres";
	
	private int saveDb(String context_sequence, int context_count,
			int context_registered) {

		int row = 0;
		int r = 0;
		try {

			// Statement stm = null;
			Connection con = null;
			try {
				Class.forName("org.postgresql.Driver");

				con = DriverManager.getConnection(Param.address_db);
				// stm = con.createStatement();
			}

			catch (Exception e) {
				e.printStackTrace();
			}

			String sql = "INSERT INTO context (context_sequence, context_count, context_registered, context_name, enable_0_disable_1) values (?, ?, ?, ?, ?)";

			PreparedStatement statement = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, context_sequence);
			statement.setInt(2, context_count);
			statement.setInt(3, context_registered);
			statement.setString(4, "DEMO CLASS");
			statement.setInt(5, 0);

			row = statement.executeUpdate();

			ResultSet keyset = statement.getGeneratedKeys();

			if (keyset.next()) {
				// Retrieve the auto generated key(s).
				r = keyset.getInt(1);
			}

			if (row > 0) {
				System.out.println("Save in database");
			}
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return r;
	}

	private void updateDb(String context_sequence, int context_count,
			int context_registered, int index) {

		Statement stm = null;
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(Param.address_db);
			stm = con.createStatement();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		String value = null;

		try {
			ResultSet rs = stm
					.executeQuery("select * from context where id_context = '"
							+ index + "'");
			System.out.println(index);

			if (rs.next()) {

				value = rs.getString("context_sequence");
			}
			// rs.getRef(columnLabel)
			System.out.println(value);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int row = 0;

		try {
			String sql = "UPDATE context SET context_sequence = ? , context_count = ?, context_registered = ? WHERE id_context = '"
					+ index + "'";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, value + context_sequence);
			statement.setInt(2, context_count);
			statement.setInt(3, context_registered);
			//statement.setInt(4, index);

			row = statement.executeUpdate();

			if (row > 0) {
				System.out.println("Update has done");
			}

			con.close();

		} catch (SQLException e) {

		}
	}

	private int[] FuncRecursiva(String elemento, int sensor, int maior_fusao_v,
			int maior_fusao_r) {

		int idvirtual_sensor = sensor;
		String lista2real = "0";
		String lista3virtual = "0";
		int fusao = 0;

		System.out.println("XXXXXXX: " + elemento);

		Statement stm = null;
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(Param.address_db);
			stm = con.createStatement();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		if (elemento == null) {

			System.out.println("------------------------------");
			System.out.println("LIGAÇÃO: SENSOR X FUSÃO");
			System.out.println("------------------------------");

			int[] nro = { 0, 0 };
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

				//FileWriter arquivo = new FileWriter(new File("teste.txt"), true);
				//PrintWriter gravarArq = new PrintWriter(arquivo);

				int j;
				int m = lista2.size();

				for (j = 0; j < m; j++) {
					// gravarArq.println("______________________________ ");
					// gravarArq.println("ID REAL DA FUSÃO: " + lista2.get(j));
					// gravarArq.println("ID VIRTUAL DA FUSÃO:  " +
					// lista3.get(j));

					lista2real = lista2.get(j);
					lista3virtual = lista3.get(j);
					ResultSet rs13 = stm
							.executeQuery("select id_resource, dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '" + elemento + "'))");

					ArrayList<String> lista13 = new ArrayList<String>();
					ArrayList<String> lista14 = new ArrayList<String>();

					while (rs13.next()) {

						lista13.add(rs13.getString("dependence_fusion_log_fk"));
						lista14.add(rs13.getString("id_resource"));

					}

					while (rs13.next()) {

						lista13.add(rs13.getString("dependence_fusion_log_fk"));
						lista14.add(rs13.getString("id_resource"));

					}

					int d = 0;
					int tttam = lista13.size();

					for (d = 0; d < tttam; d++) {
						if (lista13.get(d) == null) {
							System.out.println("LIGAÇÃO COM SENSOR:  " + lista14.get(d));

							idvirtual_sensor++;

//							gravarArq.print("S" + idvirtual_sensor + "*"
//									+ lista14.get(d) + ":" + "F"
//									+ lista3virtual + "*" + lista2real + ";");
							
							updateDb(idvirtual_sensor + "*"
									+ lista14.get(d) + ",SENSOR" +  ":" 
									+ lista3virtual + "*" + lista2real + ",FUSION" + ";", 0, 1, index_db);

							fusao = Integer.parseInt(lista3virtual);
							if (fusao > maior_fusao_v) {
								maior_fusao_v = fusao;
								maior_fusao_r = Integer.parseInt(lista2real);
								;
							}

						}
						if (lista13.get(d) != null) {
							// System.out.println("LIGAÇÃO COM OUTRA FUSÃO:  " +
							// lista13.get(d));
//							gravarArq.print(lista13.get(d) +  "*"
//									+ lista2real + ",FUSION" + ":"  + lista3virtual
//									+ "*" + lista2real + ",FUSION" + ";");
							
							updateDb(lista13.get(d) +  "*"
									+ lista2real + ",FUSION" + ":"  + lista3virtual
									+ "*" + lista2real + ",FUSION" + ";", 0, 1, index_db);

							fusao = Integer.parseInt(lista13.get(d));
							
							if (fusao > maior_fusao_v) {
								maior_fusao_v = fusao;
								maior_fusao_r = Integer.parseInt(lista2real);
								
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
								.executeQuery("select id_resource, dependence_fusion_log_fk from resource where id_resource in(select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
										+ elemento + "')) ");

						ArrayList<String> lista6 = new ArrayList<String>();
						ArrayList<String> lista7 = new ArrayList<String>();
						// ArrayList<String> lista8 = new ArrayList<String>();

						while (rs1.next()) {
							lista6.add(rs1.getString("id_resource"));
							lista7.add(rs1
									.getString("dependence_fusion_log_fk"));
						}

					}

					int[] retorno_rec = FuncRecursiva(lista5.get(i),
							idvirtual_sensor, maior_fusao_v, maior_fusao_r);
					if (retorno_rec[0] > maior_fusao_v) {
						maior_fusao_v = retorno_rec[0];
						maior_fusao_r = retorno_rec[1];
					}
				}
//				gravarArq.close();
//				arquivo.close();

//				try {
//					BufferedReader leitor = new BufferedReader(new FileReader(
//							"teste.txt"));
//					while (leitor.ready()) {
//						String linha = leitor.readLine();
//						System.out.println(linha);
//					}
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			int[] valores = new int[] { maior_fusao_v, maior_fusao_r };
			return valores;

		}

	}

	public void tracker(Timestamp time) {

		int idvirtual_regra = 1;
		int idvirtual_sensor = 0;
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
			// FileWriter arquivo = new FileWriter(new File("teste.txt"), true);
			// PrintWriter gravarArq = new PrintWriter(arquivo);

			Class.forName("org.postgresql.Driver");
			Connection con;
			con = DriverManager.getConnection(Param.address_db);
			Statement stm = con.createStatement();

			ResultSet rs1 = null;

			Date date = new Date(time.getTime());
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			String reportDate = df.format(date);

			rs1 = stm
					.executeQuery("select id_resource_fk, id_rule_fk from rule_action_log where creation_date = '"
							+ reportDate + "'");

			while (rs1.next()) {

				idvirtual_atuador++;

				// primeira gravacao

				idreal_regra = Integer.parseInt(rs1.getString("id_rule_fk"));
				// gravarArq.print("R" + idvirtual_regra + "*" +
				// (rs1.getString("id_rule_fk")) + ":" + "A" + idvirtual_atuador
				// + "*" + (rs1.getString("id_resource_fk")) + ";");

				String context_sequence = idvirtual_regra + "*"
						+ (rs1.getString("id_rule_fk")) + ",RULE" + ":" 
						+ idvirtual_atuador + "*"
						+ (rs1.getString("id_resource_fk")) + ",ACTUATOR" + ";";

				if (index_db == 0)
					index_db = saveDb(context_sequence, 0, 1);
				else
					updateDb(context_sequence, 0, 1, index_db);

			}

			ResultSet rs2 = null;

			rs2 = stm
					.executeQuery("select id_fusion_fk, id_fusion_log from fusion_log where id_fusion_log in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = ' "
							+ reportDate + "'))");

			while (rs2.next()) {

				copyrs2real = rs2.getString("id_fusion_fk");
				copyrs2virtual = rs2.getString("id_fusion_log");
			}

			ResultSet rs10 = null;

			rs10 = stm
					.executeQuery("select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '"
							+ reportDate + "')))");

			ArrayList<String> lista10 = new ArrayList<String>();

			while (rs10.next()) {

				lista10.add(rs10.getString("id_resource_fk"));

			}

			ResultSet rs11 = null;

			rs11 = stm
					.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '"
							+ reportDate + "'))))");

			ArrayList<String> lista11 = new ArrayList<String>();

			while (rs11.next()) {

				lista11.add(rs11.getString("dependence_fusion_log_fk"));

			}

			ResultSet rs = null;

			rs = stm.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '"
					+ reportDate + "'))))");

			ArrayList<String> lista = new ArrayList<String>();

			while (rs.next()) {
				lista.add(rs.getString("dependence_fusion_log_fk"));
			}

			int c = 0;
			int ttam = lista.size();

			for (c = 0; c < ttam; c++) {
				if (lista.get(c) == null) {

					idvirtual_sensor++;

					// gravarArq.print("S" + idvirtual_sensor + "*" +
					// lista10.get(c) + ":" + "F" + copyrs2virtual + "*" +
					// copyrs2real + ";");

					updateDb(idvirtual_sensor + "*" + lista10.get(c) + ",SENSOR" 
							+ ":" + copyrs2virtual + "*" + copyrs2real + ",FUSION" 
							+ ";", 0, 1, index_db);

					fusao = Integer.parseInt(copyrs2virtual);
					if (fusao > maior_fusao_v) {
						maior_fusao_v = fusao;
						maior_fusao_r = Integer.parseInt(copyrs2real);
					}

				} else {

					updateDb(lista11.get(c) + "*" + copyrs2real + ",FUSION" + ":"
							+ copyrs2virtual + "*" + copyrs2real + ",FUSION" + ";",
							0, 1, index_db);

					fusao = Integer.parseInt(lista11.get(c));
					if (fusao > maior_fusao_v) {
						maior_fusao_v = fusao;
						maior_fusao_r = Integer.parseInt(copyrs2real);
					}

					fusao = Integer.parseInt(copyrs2virtual);
					if (fusao > maior_fusao_v) {
						maior_fusao_v = fusao;
						maior_fusao_r = Integer.parseInt(copyrs2real);
					}

				}
			}

			ResultSet rs4 = null;

			rs4 = stm
					.executeQuery("select id_fusion_fk, id_fusion_log from fusion_log where id_fusion_log in (select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '"
							+ reportDate + "')))))");

			ArrayList<String> lista2 = new ArrayList<String>();
			ArrayList<String> lista3 = new ArrayList<String>();

			while (rs4.next()) {
				lista2.add(rs4.getString("id_fusion_fk"));
				lista3.add(rs4.getString("id_fusion_log"));
			}

			int j;
			//int m = lista.size();
			int m = lista2.size();
			for (j = 0; j < m; j++) {
				if (lista.get(j) != null) {

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
							// gravarArq.println("LIGAÇÃO COM SENSOR:  " +
							// lista14.get(d));

							idvirtual_sensor++;
							// gravarArq.print("S" + idvirtual_sensor + "*" +
							// lista14.get(d) + ":" + "F" + lista3virtual + "*"
							// + lista2real + ";");

							updateDb(
									 idvirtual_sensor + "*"
											+ lista14.get(d) + ",SENSOR" + ":" 
											+ lista3virtual + "*" + lista2real + ",FUSION"
											+ ";", 0, 1, index_db);

							fusao = Integer.parseInt(lista3virtual);
							if (fusao > maior_fusao_v) {
								maior_fusao_v = fusao;
								maior_fusao_r = Integer.parseInt(lista2real);
							}

						}
						if (lista13.get(d) != null) {
							// gravarArq.println("LIGAÇÃO COM OUTRA FUSÃO:  " +
							// lista13.get(d));

							// gravarArq.print("F" + lista13.get(d) + "*" +
							// lista2real + ":" + "F" + lista3virtual + "*" +
							// lista2real + ";");
							updateDb(lista13.get(d) + "*" + lista2real + ",FUSION" 
									+ ":" + lista3virtual + "*"
									+ lista2real + ",FUSION" +  ";", 0, 1, index_db);

							fusao = Integer.parseInt(lista13.get(d));
							if (fusao > maior_fusao_v) {
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

				int[] retorno_rec = FuncRecursiva(lista.get(i),
						idvirtual_sensor, maior_fusao_v, maior_fusao_r);
				if (retorno_rec[0] > maior_fusao_v) {
					maior_fusao_v = retorno_rec[0];
					maior_fusao_r = retorno_rec[1];
				}

				// gravarArq.print("R" + idvirtual_regra + "*" + idreal_regra +
				// ":" + "F" + maior_fusao_v + "*" + maior_fusao_r + ";");
//				updateDb(idvirtual_regra + "*" + idreal_regra + "RULE" +  ":" 
//						+ maior_fusao_v + "*" + maior_fusao_r +  "FUSION" + ";", 0, 1,
//						index_db);
				
				updateDb(maior_fusao_v + "*" + maior_fusao_r +  ",FUSION" + ":" + idvirtual_regra + "*" + idreal_regra + ",RULE" +  ";", 0, 1,
						index_db);
				System.out.println("FUSION --> RULE : " + maior_fusao_v + "*" + maior_fusao_r +  ",FUSION" + ":" + idvirtual_regra + "*" + idreal_regra + ",RULE");

			}

			// gravarArq.close();
			// arquivo.close();

			// try {
			// BufferedReader leitor = new BufferedReader(new
			// FileReader("teste.txt"));
			// while(leitor.ready()){
			// String linha = leitor.readLine();
			// System.out.println(linha);
			// }
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// }

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}