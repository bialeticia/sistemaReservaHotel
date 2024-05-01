package sistemaReservaHotel;
import java.util.List;

public class Quarto {
	private int numero;
	private EstadoQuarto estado = EstadoQuarto.disponivel;
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
	
	public List<Hospede> getHospedes() {
		return hospedes;
	}

	public synchronized void adicionarHospede(List<Hospede> hospedes) {
		if (this.hospedes.size() < capacidade) {
			this.hospedes = hospedes;
			this.setEstado(EstadoQuarto.ocupado);
		}
	}
	
	public synchronized void removerHospede(Hospede hospede) {
		hospedes.remove(hospede);
		
		if (hospedes.isEmpty() && this.getEstado() == EstadoQuarto.ocupado) {
			this.setEstado(EstadoQuarto.sujo);
		}
	}
	
	public boolean disponivel() {
		if(this.getEstado() == EstadoQuarto.disponivel) {
			return true;
		}
		
		return false;
	}
}
