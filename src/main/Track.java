package main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Track {

	private String location;
	private String title;
	private String creator;
	private String album;
	private String image;
	private int trackNum;
	private int identifier;
	private String code;
	private String owner;
	
	public boolean gravaMusica() throws IOException {
		
		Baixador baixador = new Baixador();

		File diretorioAutor = new File(this.creator); 
		if(!diretorioAutor.exists()) {
			diretorioAutor.mkdir();
		}
		
		File diretorioAlbum = new File(diretorioAutor, this.album);
		if(!diretorioAlbum.exists()) {
			diretorioAlbum.mkdir();
		}
		
		File nomeArquivo = new File(diretorioAlbum, this.title);
		
		
		baixador.baixa(this.location, nomeArquivo);
		
		File nomeImagem = new File(diretorioAlbum, this.album);
		
		
		baixador.baixa(this.image, nomeImagem);
		return true;
	}
	
	

	public String getOwner() {
		return owner;
	}
	public String getLocation() {
		return location;
	}
	public String getTitle() {
		return title;
	}
	public String getCreator() {
		return creator;
	}
	public String getAlbum() {
		return album;
	}
	public String getImage() {
		return image;
	}
	public int getTrackNum() {
		return trackNum;
	}
	public int getIdentifier() {
		return identifier;
	}
	public String getCode() {
		return code;
	}
	
	
}
