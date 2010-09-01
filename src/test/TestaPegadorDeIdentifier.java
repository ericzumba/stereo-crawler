package test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import main.PaginaDeSong;
import main.PegadorDeIdentifier;

import org.junit.Test;
import static org.mockito.Mockito.when;;


public class TestaPegadorDeIdentifier {

	@Test
	public void pegaIdentifierDaPaginaSong() throws Exception {
		String conteudo = "http://stereomood.com/song/bnN4TGFuQlBVVWRsYVpkckV1WDlCbG9nbFp6b25HNTB6ODhYSDdLRStpYz0%253D/shuffle_playlist.html?save&index=' + get_actual_page(),";
		
		PaginaDeSong paginaSong = new PaginaDeSong("qualquer url");
		
		when(paginaSong.getConteudo()).thenReturn(conteudo);
		
		PegadorDeIdentifier pegadorDeIdentifier = new PegadorDeIdentifier(paginaSong);
		String identifier = pegadorDeIdentifier.pega();
		System.out.println("indentifier is :" + identifier);
		Assert.assertEquals("bnN4TGFuQlBVVWRsYVpkckV1WDlCbG9nbFp6b25HNTB6ODhYSDdLRStpYz0%253D", identifier);
	}
}
