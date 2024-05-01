package sistemaReservaHotel;
import java.util.List;

public class Hotel {
	private List<Recepcionista> recepcionistas;
	private List<Camareira> camareiras;
	private List<Quarto> quartos;
	private List<Quarto> quartosParaLimpeza;

	public Hotel(List<Recepcionista> recepcionistas, List<Camareira> camareiras, List<Quarto> quartos) {
		this.recepcionistas = recepcionistas;
		this.camareiras = camareiras;
		this.quartos = quartos;
	}

	public void tentarCheckIn(List<Hospede> hospedes) {
		for (Recepcionista recepcionista: recepcionistas) {
			recepcionista.realizarCheckIn(quartos, hospedes);
		}
	}
	
	public synchronized Quarto obterProximoQuartoParaLimpar() {
		if(!quartosParaLimpeza.isEmpty()) {
			return quartosParaLimpeza.remove(0);
		}
        
        return null;
    }
	
	public void setQuartosParaLimpeza(Quarto quarto) {
		quartosParaLimpeza.add(quarto);
	}

	public List<Quarto> getQuartos() {
		return quartos;
	}
	
}
