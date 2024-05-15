package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.*;
import graph.GraphD;
import menu.Usermenu;

public class Main {
	public List<UsuarioConM> users;
	public Medidas med;
	public static Scanner teclado = new Scanner(System.in);
	public Usermenu menu = new Usermenu();
	public static void main(String args[]) {
		Main main = new Main();
		main.start();
	}
	public void start() {
		users = new ArrayList<UsuarioConM>();
		boolean good = true;
		boolean finish = false;
		do {
			do {
				System.out.println("Display Users (1) or New User(2) or exit (3)");
				int op = teclado.nextInt();
				if (op == 1) {
					if(displayU()) {
						int op2 = menu.seeU(teclado);
						if (op2 > 0) {
							meds(op2-1);
						}
					}
				}
				else if (op == 2) {
					users.add(menu.NewUser(teclado));
				}
				else if (op == 3) {
					System.exit(0);
				}
				else {
					System.out.println("Bad Option, try again");
					good = false;
				}
			}while (!good);
		}while(!finish);
	}
	public void meds(int userI) {
		boolean good = true;
		boolean finish = false;
		do {
			do {
				System.out.println("Do you want to see old measurements for this User (1) or take a new measurement (2) or go back to Users Screen(3))");
				int op = teclado.nextInt();
				if (op == 1) {
					displayM(userI);
					int op2 = menu.seeM(teclado);
					if (op2 > 0) {
					    GraphD graf = new GraphD(users.get(userI).getMed().get(op2).getMedida());
					    graf.StartGraph(users.get(userI).getMed().get(op2).getMedida());
					}
				}
				else if (op == 2) {
					if (users.get(userI).getMed().isEmpty()) {
						System.out.println("There are no previous measurements , taking first measurement :  ");
					}
					users.get(userI).setMed(menu.newMed(users.get(userI)));
				}
				else if(op == 3) {
					finish = true;
				}
				else {
					System.out.println("Bad Option, try again");
					good = false;
				}
			}while (!good);
		}while(!finish);
	}

	public boolean displayU() {
		if (users.isEmpty() ) {
			System.out.println("There are no Users");
			return false;
		}else {
			for(int i = 0 ; i < users.size() ; i++) {
				System.out.println(users.get(i).getNombre() + "  --" + (i+1));
			}
			return true;
		}

	}
	public boolean displayM(int userI) {
		if (users.get(userI).getMed().isEmpty()) {
			System.out.println("There are no Measurements");
			return false;
		}else {
			for(int i = 0 ; i < users.get(userI).getMed().size() ; i++) {
				System.out.println(users.get(userI).getMed().get(i).getFecha() + "  --" + (i+1));
			}
			return true;
		}

	}
}