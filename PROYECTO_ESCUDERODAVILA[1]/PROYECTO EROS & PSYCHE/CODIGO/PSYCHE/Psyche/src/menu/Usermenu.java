package menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import connect.Server;
import data.*;
import graph.GraphD;

public class Usermenu {
public UsuarioConM NewUser(Scanner teclado) {
	UsuarioConM user = null;
	System.out.println(" Name : ");
	String nom = teclado.next();
	System.out.println("Do you want to take a measurement of this User? true or false");
	boolean op = teclado.nextBoolean();
	if (op) {
		user = new UsuarioConM(nom);
		user.setMed(newMed(user));
		
	}else {
        user = new UsuarioConM(nom);
	}
	return user;
}
public int seeU(Scanner teclado) {
	System.out.println("Do you want to see the measurements for a User? input the User number to do so if not input 0 : ");
	return teclado.nextInt();
}
public int seeM(Scanner teclado) {
	System.out.println("Do you want to see a measurement ? input the measurement number to do so if not input 0 : ");
	return teclado.nextInt();
}
public List<Medidas> newMed(UsuarioConM userI){
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDateTime now = LocalDateTime.now();
	Server sv = new Server();
	List<Medidas> medidasv = userI.getMed();
	medidasv.add(new Medidas(sv.ServerC(),dtf.format(now)));
    GraphD graf = new GraphD(medidasv.get(0).getMedida());
    graf.StartGraph(medidasv.get(0).getMedida());
	return medidasv;
}
}
