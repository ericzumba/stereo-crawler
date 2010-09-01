package main;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numeroUrl = 1;
		
		while(numeroUrl < 50000) {
			PaginaDeSong paginaDeSong = new PaginaDeSong("http://www.stereomood.com/song/" + numeroUrl);
			
			if(paginaDeSong.naoEh404()) {
				System.out.println(paginaDeSong.getXml());
			}
			
			
			
			numeroUrl++;
			
		}

	}

}
