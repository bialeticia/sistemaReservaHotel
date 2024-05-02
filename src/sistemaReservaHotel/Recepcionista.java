package sistemaReservaHotel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Queue;
import java.util.LinkedList;

public class Recepcionista extends Thread {
	UUID id = UUID.randomUUID();
	Hotel hotel;
	private Queue<List<Hospede>> filaDeEspera = new LinkedList<>();
	
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
					defineQuarto(hospede, quarto);
					return;
				}
			}
			filaDeEspera.add(hospede);
		}
		
		System.out.println("Não foi possível realizar o checkIn");
	}
	
	 public synchronized void quartoFicouDisponivel(List<Quarto> quartos) {
	        if (!filaDeEspera.isEmpty()) {
	            List<Hospede> hospede = filaDeEspera.poll();
	            realizarCheckIn(quartos, hospede);
	        }
	    }
	
	public void defineQuarto(List<Hospede> hospedes, Quarto quarto) {
		for (Hospede hospede: hospedes) {
			hospede.setQuarto(quarto);
		}
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