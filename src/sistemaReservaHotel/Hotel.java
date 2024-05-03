package sistemaReservaHotel;
import java.util.List;
import java.util.Iterator;

public class Hotel {
	private List<Recepcionista> recepcionistas;
	private List<Camareira> camareiras;
	private List<Quarto> quartos;
	private List<Quarto> quartosParaLimpeza;
	private List<Hospede> hospedes;
	
	public Hotel(List<Recepcionista> recepcionistas, List<Camareira> camareiras, List<Quarto> quartos) {
		this.recepcionistas = recepcionistas;
		this.camareiras = camareiras;
		this.quartos = quartos;
	}

	public void tentarCheckIn() {
		for (Recepcionista recepcionista: recepcionistas) {
			recepcionista.realizarCheckIn(hospedes);
		}
	}
	
	public void realizarCheckout(Hospede hospede) {
		for (Recepcionista recepcionista: recepcionistas) {
			recepcionista.setFilaDeCheckout(hospede);;
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

	public List<Hospede> getHospedes() {
		return hospedes;
	}

	public void setHospedes(List<Hospede> hospedes) {
		this.hospedes = hospedes;
	}
	
	public void removeHospedes(List<Hospede> hospedes) {
		Iterator<Hospede> iterator = this.hospedes.iterator();
        while (iterator.hasNext()) {
            Hospede hospedeAtual = iterator.next();
            if (hospedes.contains(hospedeAtual)) {
                iterator.remove();
            }
        }
	}
}
