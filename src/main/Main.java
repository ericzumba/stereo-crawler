package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {

	static File setup = new File("setup.properties");
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(setup);
		String line = scanner.nextLine();
		System.out.println(line);
		Integer numeroUrl = Integer.parseInt(line);
		
		
		
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
					track.gravaMusica();
				} catch (Exception e) {
					System.out.println("Houve um problema ao gravar a faixa");
					e.printStackTrace();
				}
			}

			numeroUrl++;
			
			escreve(numeroUrl);
		}

	}

	private static void escreve(Integer numeroUrl) throws IOException {
		
		setup.delete();
		
		FileWriter writer = new FileWriter(setup);
		writer.write(numeroUrl.toString());
		writer.flush();	
		writer.close();
	
	}

}
