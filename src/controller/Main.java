package controller;

import front.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import back.*;
public class Main {
	private static Go game;
	
	public static void main(String[] args) {
		// Argumentos
		// java -jar tpe.jar (-visual | -file archivo -player n) (-maxtime n |
		// -depth n) [-prune] [-tree]

		
		int cantidadArgumentos = args.length;

		if (cantidadArgumentos < 3 || cantidadArgumentos > 8 || cantidadArgumentos == 5) {
			mensajeErrorParametros();
			return;
		}

		switch (cantidadArgumentos) {
		case 3:
		case 4:
			if (args[0].equals("-visual")) {
				if (args[1].equals("-maxtime")) {
					try {
						long n = Long.parseLong(args[2]);

						if (n < 0) {
							mensajeErrorParametros();
							return;
						}

						if (n > 1000000) {
							System.out.println("maxtime muy alto, el maxtime no puede ser mayor a 1000000");
							return;
						}

						if (cantidadArgumentos == 4 && !args[3].equals("-prune")) {
							mensajeErrorParametros();
							return;
						}

						game = new Go(username(), n);

					} catch (NumberFormatException e) {
						mensajeErrorParametros();
						return;
					}
				} else if (args[1].equals("-depth")) {
					try {
						int n = Integer.parseInt(args[2]);

						if (n < 0) {
							mensajeErrorParametros();
							return;
						}

						if (n > 8) {
							System.out.println("depth muy alto, el depth no puede ser mayor a 8");
							return;
						}

						if (cantidadArgumentos == 4 && !args[3].equals("-prune")) {
							mensajeErrorParametros();
							return;
						}

						game = new Go(username(), n);

					} catch (NumberFormatException e) {
						mensajeErrorParametros();
						return;
					}
				} else {
					mensajeErrorParametros();
					return;

				}

			} else {
				mensajeErrorParametros();
				return;
			}
			break;

		case 6: case 7: case 8:
			if (args[0].equals("-file")) {
				Tablero t = readFile(args[1]);
				
				if(t == null)
					return;
				
				if (args[2].equals("-player")) {
					int player = Integer.parseInt(args[3]);
					if (player == 1 || player == 2) {
						if (args[4].equals("-maxtime")) {
							try {
								long n = Long.parseLong(args[5]);

								if (n < 0) {
									mensajeErrorParametros();
									return;
								}

								if (n > 1000000) {
									System.out.println("maxtime muy alto, el maxtime no puede ser mayor a 1000000");
									return;
								}
								
								if ((cantidadArgumentos == 7 && (!args[6].equals("-tree") && !args[6].equals("-prune"))) || (cantidadArgumentos == 8 && (!args[7].equals("-tree") && !args[6].equals("-prune")))) {
									mensajeErrorParametros();
									return;
								}
								
								if((cantidadArgumentos == 7 && args[6].equals("-tree")) || (cantidadArgumentos == 8) && args[7].equals("-tree") ){
									game = new Go(player, n, t, true);
									return;
								}
								
								game = new Go(player, n, t, false);
								return;

							} catch (NumberFormatException e) {
								mensajeErrorParametros();
								return;
							}
						} else if (args[4].equals("-depth")) {
							try {
								int n = Integer.parseInt(args[5]);

								if (n < 0) {
									mensajeErrorParametros();
									return;
								}

								if (n > 8) {
									System.out.println("depth muy alto, el depth no puede ser mayor a 8");
									return;
								}

								if ((cantidadArgumentos == 7 && (!args[6].equals("-tree") && !args[6].equals("-prune"))) || (cantidadArgumentos == 8 && (!args[7].equals("-tree") && !args[6].equals("-prune")))) {
									mensajeErrorParametros();
									return;
								}
								
								if((cantidadArgumentos == 7 && args[6].equals("-tree")) || (cantidadArgumentos == 8) && args[7].equals("-tree") ){
									game = new Go(player, n, t, true);
									return;
								}
								
								game = new Go(player, n, t, false);
								return;

							} catch (NumberFormatException e) {
								mensajeErrorParametros();
								return;
							}
						} else {
							mensajeErrorParametros();
							return;
						}
					}else {
						mensajeErrorParametros();
						return;
					}
				}else {
					mensajeErrorParametros();
					return;
				}

			} else {
				mensajeErrorParametros();
				return;
			}
		}
		
		Listeners listener = new Listeners(game);

		// if(args[0]=="visual")
		GUI.iniciar();
		// else{
		// Por consola con parametros file y player
		// }

	}
	
	private static Tablero readFile(String path){
		FileInputStream fIn = null;
		try {
			fIn = new FileInputStream(path);
			Tablero t = new Tablero();
			int c, index=0;
			Jugador j = new Jugador("Jugador 1", false, false);
			Jugador m = new Jugador("Jugador 2", true,true);
			while((c = fIn.read()) != -1){
				switch (c) {
				case 1:
					t.agregarFicha(j, index/13, index%13);
					break;
				case 2:
					t.agregarFicha(m, index/13, index%13);
					break;
				default:
					t.agregarFicha(null, index/13, index%13);
					break;
				}					
			}
			fIn.close();
			return t;
		} catch (Exception e) {
			System.out.println("Path no valido.");
			return null;
		}
	}

	private static String username() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese su nombre: ");
		String user = scanner.next();

		return user;
	}

	private static void mensajeErrorParametros() {
		System.out.println("Error de parámetros");
		System.out.println("Formato correcto:");
		System.out.println(
				"java -jar tpe.jar (-visual | -file archivo -player n) (-maxtime n | -depth n) [-prune] [-tree]");
		System.out.println("Saliendo del programa");
	}
	
	

	public static Go obtenerGo() {
		return game;
	}
}