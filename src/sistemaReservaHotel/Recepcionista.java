package sistemaReservaHotel;
import java.util.List;
import java.util.UUID;

public class Recepcionista extends Thread {
	UUID id = UUID.randomUUID();
	Hotel hotel;
	
	public Recepcionista(Hotel hotel) {
		this.hotel = hotel;
	}

	public void run() {
       
    }
	
	public synchronized void realizarCheckIn(List<Quarto> quartos, List<Hospede> hospedes) { 
		for (Quarto quarto: quartos) {
			if (quarto.disponivel()) {
				quarto.adicionarHospede(hospedes);
				return;
			}
		}
		System.out.println("Não foi possível realizar o checkIn");
	}
	
	public void adicionarQuartoParaLimpeza() {
		for (Quarto quarto: hotel.getQuartos()) {
			for (Hospede hospede: quarto.getHospedes()) {
				if (hospede.getSituacaoHospede() == SituacaoHospede.quarto) {
					break;
				}
				hotel.setQuartosParaLimpeza(quarto);
			}
		}
	}
}
