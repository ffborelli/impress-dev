package br.ufabc.impress;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class teste3 {

	public static String FuncRecursiva(String elemento) {

		// select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk
		// = --> qual resorce log --> qual recurso foi criado
		if (elemento == null) {

			System.out.printf("Chegou em uma folha\n");
			return null;

		} else {

			try {

				Class.forName("org.postgresql.Driver");
				Connection con;
				con = DriverManager
						.getConnection("jdbc:postgresql://localhost:5432/impress_2?user=postgres&password=postgres");
				Statement stm = con.createStatement();

				System.out
						.println("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
								+ elemento + "'))");
				ResultSet rs = stm
						.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk = '"
								+ elemento + "'))");

				ArrayList<String> lista2 = new ArrayList<String>();

				while (rs.next()) {
					lista2.add(rs.getString("dependence_fusion_log_fk"));
				}
				System.out.println(lista2);

				int i;
				System.out
						.printf("Chama a função recursiva para todos os elementos da lista\n");
				int n = lista2.size();
				for (i = 0; i < n; i++) {
					FuncRecursiva(lista2.get(i));
				}

				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;

		}

	}

	public static void main(String[] args) {

		try {

			// select id_rule_action_log from rule_action_log where
			// creation_date = '15/10/29 16:26:54' --> qual regra
			// select id_fusion_log_fk from fusion_rule_log where
			// id_rule_action_log_fk in --> qual fusão
			// select id_resource_log_fk from rsc_fusion_log where
			// id_fusion_log_fk in --> qual recurso
			// select dependence_fusion_log_fk from resource where id_resource
			// in (select id_resource_fk from resource_log where id_resource_log
			// --< vê se tem dependencia de recursão
			Class.forName("org.postgresql.Driver");
			Connection con;
			con = DriverManager
					.getConnection("jdbc:postgresql://localhost/impress_2?user=postgres&password=postgres");
			Statement stm = con.createStatement();

			System.out
					.println("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log))))");
			// System.out.println("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '15/10/29 16:26:54'))))");
			// ResultSet rs =
			// stm.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log where creation_date = '15/10/29 16:26:54'))))");
			ResultSet rs = stm
					.executeQuery("select dependence_fusion_log_fk from resource where id_resource in (select id_resource_fk from resource_log where id_resource_log in (select id_resource_log_fk from rsc_fusion_log where id_fusion_log_fk in (select id_fusion_log_fk from fusion_rule_log where id_rule_action_log_fk in (select id_rule_action_log from rule_action_log))))");

			ArrayList<String> lista = new ArrayList<String>();

			while (rs.next()) {
				lista.add(rs.getString("dependence_fusion_log_fk"));
			}
			System.out.println(lista);

			int i;
			System.out
					.printf("Chama a função recursiva para todos os elementos da lista\n");
			int n = lista.size();

			for (i = 0; i < n; i++) {
				FuncRecursiva(lista.get(i));
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}