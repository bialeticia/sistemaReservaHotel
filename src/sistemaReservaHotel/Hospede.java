package sistemaReservaHotel;

public class Hospede extends Thread {
	private String nome;
	private Quarto quarto;
	private Hotel hotel;
	
	public Hospede(String nome, Hotel hotel) {
		this.nome = nome;
		this.hotel = hotel;
	}
	
	public void run() {
		try {
            while (!interrupted()) {
            	if (quarto != null) {
            		if (Math.random() < 0.5) {
                		if (quarto.getSituacaoHospede() == SituacaoHospede.quarto) {
                			System.out.println("Entrou aqui 1");
                			quarto.sairDoQuarto(); 
                		} else {
                			System.out.println("Entrou aqui 2");
                			quarto.tentarVoltarAoQuarto();
                		}
                    } else {
                    	hotel.realizarCheckout(this);
                    	quarto.removerHospede(this);
                    }
            	}
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Hospede interrompido: " + e.getMessage());
        }
    }
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
