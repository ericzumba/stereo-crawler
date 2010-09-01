package test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import main.PaginaDeSong;

import org.junit.Test;


public class PegadorDeConteudoTest {

	@Test
	public void pegaConteudoDeUmaPaginaDeSong() throws Exception {
		PaginaDeSong paginaDeSong1 = new PaginaDeSong("http://www.stereomood.com/song/1");
		System.out.println(paginaDeSong1.getConteudo());
		
		Assert.assertNotNull(paginaDeSong1.getConteudo());
	}
}
