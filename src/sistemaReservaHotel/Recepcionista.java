package sistemaReservaHotel;
import java.util.ArrayList;
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
		List<List<Hospede>> sublistaHospede = divideHospedes(hospedes);
		
		for (List<Hospede> hospede: sublistaHospede) {
			for (Quarto quarto: quartos) {
				if (quarto.disponivel()) {
					quarto.adicionarHospede(hospede);
					return;
				}
			}
		}
		
		System.out.println("Não foi possível realizar o checkIn");
	}
	
	public List<List<Hospede>> divideHospedes(List<Hospede> hospedes) {
		List<List<Hospede>> sublistaHospede = new ArrayList<>();
				
		for (int comeco = 0; comeco < hospedes.size(); comeco += 4) {
			int finalDaLista = Math.min(comeco + 4, hospedes.size());
			List<Hospede> sublista = hospedes.subList(comeco, finalDaLista);
			sublistaHospede.add(new ArrayList<>(sublista));
		}
		
		return sublistaHospede;
	}
	
	public void adicionarQuartoParaLimpeza() {
		for (Quarto quarto: hotel.getQuartos()) {
			if (quarto.getSituacaoHospede() == SituacaoHospede.quarto) {
				break;
			}
			hotel.setQuartosParaLimpeza(quarto);
		}
	}
}