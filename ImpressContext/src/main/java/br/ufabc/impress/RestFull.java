package br.ufabc.impress;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;

import org.json.JSONObject;


public class RestFull {

	private final String USER_AGENT = "Mozilla/5.0";

//	public static void main(String[] args) throws Exception {
//
//		RestFull http = new RestFull();
//
//		System.out.println("Testing 1 - Send Http GET request");
//		http.sendGet();
//		
//		System.out.println("\nTesting 2 - Send Http POST request");
//		http.sendPost();
//
//	}

	// HTTP GET request
	public void sendGet(String url) throws Exception {

		//String url = "http://www.google.com/search?q=mkyong";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");
		//con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("appID", "contextManager");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	// HTTP POST request
	public void sendPost(String url) throws Exception {
				
		//String url=Param.address_rai_rest+"411cf463-e5bb-303d-901b-2287a92fdd63" + "/authorize_access";
		//String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);	
		//java.net.URL obj = new URL(null, url,new sun.net.www.protocol.https.Handler());
		//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");

		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes("{ \"appID\" : \"contextManager\", \"priority\" : \"1\", \"securityLevel\" : \"none\" }\" ");
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}
	
	
	public void sendPut(String url) throws Exception {
		
		//String url=Param.address_rai_rest+"411cf463-e5bb-303d-901b-2287a92fdd63" + "/authorize_access";
		//String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);	
		//java.net.URL obj = new URL(null, url,new sun.net.www.protocol.https.Handler());
		//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		//add reuqest header
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");

		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes("{ \"R\" : 254 , \"G\" : 254 , \"B\" : 254 }\" ");
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'PUT' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}
}
