package sistemaReservaHotel;

import java.util.List;

public class Recepcionista extends Thread {
	int id;
	
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
}
