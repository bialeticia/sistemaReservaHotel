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
	private List<Hospede> filaDeCheckout;
	
	public Recepcionista(Hotel hotel) {
		this.hotel = hotel;
	}

	public void run() {
		try {
            while (!interrupted()) {
            	realizarCheckIn(hotel.getHospedes());
            	Thread.sleep(500);
            	tentarCheckIn();
            	Thread.sleep(500);
            	realizarCheckout();
            }
        } catch (InterruptedException e) {
            System.out.println("Recepcionista interrompido: " + e.getMessage());
        }
	}
	
	public synchronized boolean realizarCheckIn(List<Hospede> hospedes) { 
		List<List<Hospede>> sublistaHospede = divideHospedes(hospedes);
		
		for (List<Hospede> hospede: sublistaHospede) {
			for (Quarto quarto: hotel.getQuartos()) {
				if (quarto.disponivel()) {
					quarto.adicionarHospede(hospede);
					defineQuarto(hospede, quarto);
					return true;
				}
			}
			filaDeEspera.add(hospede);
		}
		
		System.out.println("Não foi possível realizar o checkIn");
		return false;
	}
	
	public synchronized void tentarCheckIn() {
		boolean checkIn = false;
		
		if (!filaDeEspera.isEmpty()) {
			List<Hospede> hospede = filaDeEspera.poll();
			checkIn = realizarCheckIn(hospede);
		}
		
		if (!checkIn) {
			System.out.println("Reclamação: Não houveram quartos.");
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
	
	public List<Hospede> getFilaDeCheckout() {
		return filaDeCheckout;
	}

	public void setFilaDeCheckout(Hospede filaDeCheckout) {
		this.filaDeCheckout.add(filaDeCheckout);
	}
	
	public void realizarCheckout() {
		if (!this.getFilaDeCheckout().isEmpty()) {
			hotel.removeHospedes(filaDeCheckout);;
		}
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