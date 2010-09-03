package main;

import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numeroUrl = 1;
		
		if(args[0] != null) {
			numeroUrl = Integer.parseInt(args[0]);
		}
		
		
		while(numeroUrl < 50000) {
			PaginaDeSong paginaDeSong = new PaginaDeSong("http://www.stereomood.com/song/" + numeroUrl);
			
			if(paginaDeSong.naoEh404()) {
				String xml = paginaDeSong.getXml();
				
				System.out.println("XML é: " + xml);
				XStream xStream = new XStream(new DomDriver());
			
				Playlist playlist = new Playlist();
				
				xStream.alias("playlist", Playlist.class);
				xStream.alias("track", Track.class);
				
				playlist = (Playlist) xStream.fromXML(xml, playlist);
				Track track = playlist.getTrackList().get(0);
				System.out.println(track.getLocation());
				
				try {
					track.grava();
				} catch (IOException e) {
					System.out.println("Houve um problema ao gravar a faixa");
					e.printStackTrace();
				}
			}

			numeroUrl++;
			
			escreve(numeroUrl);
		}

	}

	private static void escreve(int numeroUrl) {
		
		
	}

}
