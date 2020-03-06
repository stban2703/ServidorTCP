package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import processing.core.PApplet;

public class Main extends PApplet {

	public static void main(String[] args) {
		PApplet.main("view.Main");

	}

	public void settings() {
		size(500, 500);

	}

	public void setup() {

		// Este elemento nos permite esperar una conexión

		new Thread(

				() -> {
					{
						try {
							ServerSocket server = new ServerSocket(5000);

							// Esta linea permite aceptar conexión entrante automáticamente
							System.out.println("Esperando...");
							Socket socket = server.accept();
							System.out.println("Conexión aceptada");

							// Importar de java.io
							InputStream is = socket.getInputStream();

							InputStreamReader isr = new InputStreamReader(is);

							BufferedReader reader = new BufferedReader(isr);

							while (true) {
								String line = reader.readLine();
								System.out.println(line);

								switch (line) {
								case "UP":
									y -= 10;
									break;

								case "DOWN":
									y += 10;
									break;

								case "LEFT":
									x -= 10;
									break;

								case "RIGHT":
									x += 10;
									break;

								case "COLOR":
									r = (int) random(0,255);
									g = (int) random(0,255);
									b = (int) random(0,255);
									break;

								default:
									break;
								}
							}

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

		).start();

	}

	int x = 250, y = 250;
	int r = 255, g = 0, b = 0;
	int contador = 0;

	public void draw() {
		background(0);
		/*if (contador == 0) {
			r = 255;
			g = 0;
			b = 0;
		}

		if (contador == 1) {
			r = 0;
			g = 255;
			b = 0;
		}

		if (contador == 2) {
			r = 0;
			g = 0;
			b = 255;
		}*/

		fill(r, g, b);
		ellipse(x, y, 50, 50);

	}

}
