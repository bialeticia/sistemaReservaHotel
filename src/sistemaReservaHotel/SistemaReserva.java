package sistemaReservaHotel;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SistemaReserva {

	public static void main(String[] args) {
		Random random = new Random();
		List<Hospede> hospedes = new ArrayList<>();
		
        List<Quarto> quartos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            quartos.add(new Quarto(i));
        }

        List<Recepcionista> recepcionistas = new ArrayList<>();

        List<Camareira> camareiras = new ArrayList<>();

        Hotel hotel = new Hotel(recepcionistas, camareiras, quartos);

        for (int i = 1; i <= 5; i++) {
            recepcionistas.add(new Recepcionista(hotel));
        }

        for (int i = 1; i <= 10; i++) {
            camareiras.add(new Camareira(i, hotel));
        }

        recepcionistas.forEach(Thread::start);
        camareiras.forEach(Thread::start);
        
        for (int j = 0; j < 10; j++) {
            int numeroDeHospedes = random.nextInt(5) + 1;
            List<Hospede> innerHospedes = new ArrayList<>();

            for (int i = 0; i < numeroDeHospedes; i++) {
                Hospede hospede = new Hospede("Hóspede " + (i + 1), hotel);
                innerHospedes.add(hospede);
                hospedes.add(hospede);
            }
           
            hotel.setHospedes(innerHospedes); 
            System.out.println("Lista de " + numeroDeHospedes + " hospedes adicionada ao hotel.");
        }
        
        hospedes.forEach(Thread::start);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            hospedes.forEach(Thread::interrupt);
            camareiras.forEach(Thread::interrupt);
            recepcionistas.forEach(Thread::interrupt);
        }));

	}

}
