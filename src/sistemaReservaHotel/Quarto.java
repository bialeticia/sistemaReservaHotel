package sistemaReservaHotel;
import java.util.List;

public class Quarto {
	private int numero;
	private EstadoQuarto estado = EstadoQuarto.disponivel;
	private int capacidade = 4;
	private List<Hospede> hospedes;
	private SituacaoHospede situacaoHospede;
	
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
			System.out.println("Hospede adicionado ao quarto " + numero);
		}
	}
	
	public synchronized void removerHospede(Hospede hospede) {
		hospedes.remove(hospede);
		
		if (hospedes.isEmpty() && this.getEstado() == EstadoQuarto.ocupado) {
			this.setEstado(EstadoQuarto.sujo);
			System.out.println("Hospede removido do quarto " + numero);
		}
	}
	
	public boolean disponivel() {
		if(this.getEstado() == EstadoQuarto.disponivel) {
			return true;
		}
		
		return false;
	}
	
	public void sairDoQuarto() {
		System.out.println("Hospede fora do quarto " + numero);
		this.setSituacaoHospede(SituacaoHospede.fora);
	}
	
	public void tentarVoltarAoQuarto() {
		if (this.estado == EstadoQuarto.limpo) {
			this.setSituacaoHospede(SituacaoHospede.quarto);
			System.out.println("Hospede voltou ao quarto " + numero);
		}
	}
	
	public SituacaoHospede getSituacaoHospede() {
		return situacaoHospede;
	}

	public void setSituacaoHospede(SituacaoHospede situacaoHospede) {
		this.situacaoHospede = situacaoHospede;
	}
}
