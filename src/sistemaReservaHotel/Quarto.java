package sistemaReservaHotel;
import java.util.List;

public class Quarto {
	private int numero;
	private EstadoQuarto estado = EstadoQuarto.disponivel;
	private int capacidade = 4;
	private List<Hospede> hospedes;
	private SituacaoHospede situacaoHospede;
	
	public Quarto(int numero) {
		this.numero = numero;
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
		if (hospedes.size() < capacidade) {
			this.hospedes = hospedes;
			this.setEstado(EstadoQuarto.ocupado);
			this.setSituacaoHospede(SituacaoHospede.quarto);
			SincronizarPrints.log("Hospede adicionado ao quarto " + numero);
		}
	}
	
	public synchronized void removerHospede(Hospede hospede) {
		if (hospedes != null && !hospedes.isEmpty()) {
			hospedes.remove(hospede);
			
			if (hospedes.isEmpty() && this.getEstado() == EstadoQuarto.ocupado) {
				this.setEstado(EstadoQuarto.sujo);
				SincronizarPrints.log("Hospede removido do quarto " + numero);
			}
			notifyAll();
		}
		
	}
	
	public boolean disponivel() {
		if(this.getEstado() == EstadoQuarto.disponivel) {
			return true;
		}
		
		return false;
	}
	
	public void sairDoQuarto() {
	    SincronizarPrints.log("Hospede saiu do quarto " + this.getNumero());
	    this.setSituacaoHospede(SituacaoHospede.fora);
	}
	
	public void tentarVoltarAoQuarto() {
	    if (this.getEstado() == EstadoQuarto.limpo) {
	        this.setSituacaoHospede(SituacaoHospede.quarto);
	        SincronizarPrints.log("Hospede voltou ao quarto " + this.getNumero());
	    } else {
	        SincronizarPrints.log("Hospede não pode voltar ao quarto " + this.getNumero() + " porque ainda não está limpo");
	    }
	}
	
	public SituacaoHospede getSituacaoHospede() {
		return situacaoHospede;
	}

	public void setSituacaoHospede(SituacaoHospede situacaoHospede) {
		this.situacaoHospede = situacaoHospede;
	}

	public int getNumero() {
		return numero;
	}
}
