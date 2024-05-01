package sistemaReservaHotel;

public class Camareira extends Thread {
	int id;
	
	public Camareira(int id) {
		this.id = id;
	}

	public void run() {
        
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
