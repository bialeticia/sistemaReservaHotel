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
	
	public synchronized void limparQuarto(Quarto quarto) {
		if (quarto.getEstado() == EstadoQuarto.sujo && quarto.getHospedes().size() > 0) {
			quarto.setEstado(EstadoQuarto.limpo);
			return;
		} 
		if ((quarto.getEstado() == EstadoQuarto.sujo && quarto.getHospedes().size() == 0)) {
			quarto.setEstado(EstadoQuarto.disponivel);
			return;
		}
	}
}
