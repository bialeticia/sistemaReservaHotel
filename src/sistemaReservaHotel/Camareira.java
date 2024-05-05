package sistemaReservaHotel;
import java.util.UUID;

public class Camareira extends Thread {
	UUID id = UUID.randomUUID();
	private Hotel hotel;
	
	public Camareira(Hotel hotel) {
        this.hotel = hotel;
    }

	public void run() {
		try {
            while (!interrupted()) {
                Quarto quarto = hotel.obterProximoQuartoParaLimpar();
                if (quarto != null) {
                    limparQuarto(quarto);
                } else {
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Camareira interrompida: " + e.getMessage());
        }
    }
	
	public void limparQuarto(Quarto quarto) {
	    System.out.println("Camareira " + id + " começando a limpeza do quarto: " + quarto.getNumero());
	    quarto.setEstado(EstadoQuarto.limpando);

	    try {
	        Thread.sleep(1000);
	        quarto.setEstado(EstadoQuarto.limpo);
	        System.out.println("Camareira " + id + " finalizou a limpeza do quarto: " + quarto.getNumero());
	    } catch (InterruptedException e) {
	        System.out.println("Camareira " + id + " interrompida durante a limpeza do quarto: " + quarto.getNumero());
	        Thread.currentThread().interrupt();
	    }
	}
}
