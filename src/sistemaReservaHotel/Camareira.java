package sistemaReservaHotel;

public class Camareira extends Thread {
	private int id;
	private Hotel hotel;
	
	public Camareira(int id, Hotel hotel) {
		this.id = id;
        this.hotel = hotel;
    }

	public void run() {
	    try {
	        while (!interrupted()) {
	            Quarto quarto = hotel.obterProximoQuartoParaLimpar();
	            if (quarto != null) {
	                limparQuarto(quarto);
	            } else {
	                SincronizarPrints.log("Camareira " + id + ": não encontrou quartos para limpar, aguardando mais quartos...");
	                Thread.sleep(2000); 
	            }
	        }
	    } catch (InterruptedException e) {
	        SincronizarPrints.log("Camareira interrompida: " + e.getMessage());
	    }
	}
	
	public synchronized void limparQuarto(Quarto quarto) {
	    SincronizarPrints.log("Camareira " + id + " começando a limpeza do quarto: " + quarto.getNumero());
	    quarto.setEstado(EstadoQuarto.limpando);

	    try {
	        Thread.sleep(1000);
	        quarto.setEstado(EstadoQuarto.limpo);
	        SincronizarPrints.log("Camareira " + id + " finalizou a limpeza do quarto: " + quarto.getNumero());
	    } catch (InterruptedException e) {
	        SincronizarPrints.log("Camareira " + id + " interrompida durante a limpeza do quarto: " + quarto.getNumero());
	        Thread.currentThread().interrupt();
	    }
	}
}
