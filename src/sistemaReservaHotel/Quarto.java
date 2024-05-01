package sistemaReservaHotel;
import java.util.List;

public class Quarto {
	private int numero;
	private EstadoQuarto estado = EstadoQuarto.limpo;
	private int capacidade = 4;
	private List<Hospede> hospedes;
	
	public Quarto(int numero, EstadoQuarto estado) {
		this.numero = numero;
		this.estado = estado;
	}

	public EstadoQuarto getEstado() {
		return estado;
	}

	public void setEstado(EstadoQuarto estado) {
		this.estado = estado;
	} 
	
	public synchronized void adicionarHospede(Hospede hospede) {
		if (hospedes.size() < capacidade) {
			hospedes.add(hospede);
			estado = EstadoQuarto.ocupado;
		}
	}
	
	public synchronized void removerHospede(Hospede hospede) {
		hospedes.remove(hospede);
	}
	
}
