package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class PegadorDeConteudo {
	String url;
	private HttpEntity entity;
	private boolean naoEh404 = false;


	public PegadorDeConteudo(String url) {
		this.url = url;
		this.conecta();
	}

	public void conecta() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			
			StatusLine statusLine = response.getStatusLine();
			
			System.out.println("Tentando url: " + url);
			System.out.println("Status da conexão: " + statusLine);
			
			if (entity != null) {
				this.entity = entity;
				
				if(statusLine.getStatusCode() == 200)
				{
					System.out.println("Não é 404");
					naoEh404 = true;
				} else {
					naoEh404 = false;
				}
				
				
			} 
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String pegaConteudo() {
		String conteudo = "";
		
		try {
			InputStream inputStream = entity.getContent();
			conteudo = convertStreamToString(inputStream);
			
		} catch (IOException e) {
			System.out.println("Não foi possível consumir o conteúdo");
			e.printStackTrace();
		}
		
		return conteudo;
	}
	
	public String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public boolean naoEh404() {
		return naoEh404;
	}

}
