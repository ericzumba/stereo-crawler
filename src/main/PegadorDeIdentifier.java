package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PegadorDeIdentifier {

	private PaginaDeSong paginaSong;
	
	public PegadorDeIdentifier(PaginaDeSong paginaSong) {
		this.paginaSong = paginaSong;
	}

	public String pega() {
		
		try {
			Pattern padrao = Pattern.compile("song/.{64}/");
			//System.out.println(paginaSong.getConteudo());
			Matcher matcher = padrao.matcher(paginaSong.getConteudo());
			matcher.find();
			String id = matcher.group();
			
			return extraiSong(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
		
	}

	private String extraiSong(String idMaisSong) {
		
		String id = idMaisSong.substring(5, idMaisSong.length()-1);
		System.out.println("Depois de remover song: " + id);
		return id;
	}
}
