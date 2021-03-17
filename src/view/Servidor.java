package view;
import java.util.concurrent.Semaphore;

import controller.Processamento;

public class Servidor {

	public static void main(String[] args) {
		int perm = 1;
		
		Semaphore semaforo = new Semaphore(perm);
		for(int idThread = 1; idThread <= 21; idThread++) {
			Thread processar = new Processamento(idThread, semaforo);
			processar.start();
		}
	}
}
