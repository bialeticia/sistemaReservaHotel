package sistemaReservaHotel;

public class Hospede {
	private String nome;
	private Quarto quarto;
	
	public Hospede(String nome) {
		this.nome = nome;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
}
