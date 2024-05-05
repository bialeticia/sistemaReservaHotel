package sistemaReservaHotel;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Hotel {
	private List<Recepcionista> recepcionistas;
	private List<Camareira> camareiras;
	private List<Quarto> quartos;
	private List<Quarto> quartosParaLimpeza = new ArrayList<>();
	private List<Hospede> hospedes = new ArrayList<>();
	
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
	    if (!quartosParaLimpeza.isEmpty()) {
	        Quarto quarto = quartosParaLimpeza.remove(0);
	        System.out.println("Quarto " + quarto.getNumero() + " enviado para limpeza.");
	        return quarto;
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
