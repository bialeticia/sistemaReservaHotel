package sistemaReservaHotel;

public class Hospede extends Thread {
	private String nome;
	private Quarto quarto;
	
	public Hospede(String nome) {
		this.nome = nome;
	}
	
	public void run() {
      
    }
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
}
