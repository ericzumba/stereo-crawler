package main;

public class PaginaDeSong {

	private String conteudo;
	private String url;
	private boolean naoEh404;
	private PegadorDeConteudo pegadorDeConteudo;
	private PegadorDeConteudo pegadorDeXml;
	
	
	public PaginaDeSong(String url) {
		this.url = url;
		this.pegadorDeConteudo = new PegadorDeConteudo(url);
		if(pegadorDeConteudo.naoEh404()) {
			conteudo = pegadorDeConteudo.pegaConteudo();
			System.out.println("Conteudo da página de song carregado");
		}
		naoEh404 = pegadorDeConteudo.naoEh404();
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public String getId() {
		PegadorDeIdentifier pegadorDeIdentifier = new PegadorDeIdentifier(this);
		return pegadorDeIdentifier.pega();
	}
	
	public boolean naoEh404() {
		return naoEh404;
	}

	public String getXml() {
		pegadorDeXml = new PegadorDeConteudo("http://www.stereomood.com/song/" + getId() + "/shuffle_playlist.xspf?save&index=1");
		System.out.println(pegadorDeXml.pegaConteudo());
		return null;
	}
	
}
