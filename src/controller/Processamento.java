package controller;

import java.util.concurrent.Semaphore;

public class Processamento extends Thread {
	private int idThread;
	private Semaphore semaforo;

	public Processamento(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		Chamadas();
	}
	
	private void Chamadas() {
		if (idThread % 3 == 0) {
			int BD = 1500;
			for (int i = 0; i < 2; i++) {
				Calculo();
				processaDados(BD);
			}

		}
		if (idThread % 3 == 1) {
			int BD = 1000;
			for (int i = 0; i < 3; i++) {
				Calculo();
				processaDados(BD);
			}
		}
		if (idThread % 3 == 2) {
			int BD = 1500;
			for (int i = 0; i < 3; i++) {
				Calculo();
				processaDados(BD);
			}
		}
	}
	private void processaDados(int BD) {
//------------------Inicio da se��o cr�tica-------------------
		try {
			semaforo.acquire();
			MessageProcessamentoBD(BD);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			semaforo.release();
		}
//-------------------Fim da se��o cr�tica--------------------
	}

	public void Calculo() {
		int tempo = 0;
		if (idThread % 3 == 0) {
			tempo = (int) (Math.random() * 1001) + 1000;
			MessageCalc(tempo);
		}
		if (idThread % 3 == 1) {
			tempo = (int) (Math.random() * 801) + 200;
			MessageCalc(tempo);
		}
		if (idThread % 3 == 2) {
			tempo = (int) (Math.random() * 1001) + 500;
			MessageCalc(tempo);
		}
	}

	public void MessageProcessamentoBD(int tempo) {
		try {
			System.out.println("Thread #" + idThread + " Transa��o BD...");
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void MessageCalc(int tempo) {
		try {
			System.out.println("Thread #" + idThread + " Calculando.....");
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}