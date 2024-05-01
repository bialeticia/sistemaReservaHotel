package sistemaReservaHotel;

public class Hospede extends Thread {
	private String nome;
	private Quarto quarto;
	private SituacaoHospede situacaoHospede;
	
	public Hospede(String nome) {
		this.nome = nome;
	}
	
	public void run() {
      
    }
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public SituacaoHospede getSituacaoHospede() {
		return situacaoHospede;
	}

	public void setSituacaoHospede(SituacaoHospede situacaoHospede) {
		this.situacaoHospede = situacaoHospede;
	}
}
