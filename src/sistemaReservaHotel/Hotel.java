package sistemaReservaHotel;
import java.util.List;

public class Hotel {
	private List<Quarto> quartos;

	public Hotel(List<Quarto> quartos) {
		this.quartos = quartos;
	}
	
	public synchronized void realizarCheckIn(List<Hospede> hospede) { 
		for (Quarto quarto: quartos) {
			if (quarto.disponivel()) {
				quarto.adicionarHospede(hospede);
				return;
			}
		}
		System.out.println("Não foi possível realizar o checkIn");
	}
}
