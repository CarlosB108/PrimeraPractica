/**
 * Created by carlosb108 on 5/20/16.
 */

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;

public class main {

public static void main(String [] args) throws IOException {
    //leemos el url
    Scanner sc = new Scanner(System.in);
    String url;
    System.out.print("Introduzca  el url: ");
    url = sc.nextLine();

    String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
    UrlValidator urlValidator = new UrlValidator(schemes);
    if (!urlValidator.isValid(url)) {
       System.out.println("url is invalid");
    } else {


        //Nombre de la url   Documento desde un URL
        Document doc = Jsoup.connect(url).get();
        //Titulo
        String titulo = doc.title();
        System.out.print("\n Titulo: " + titulo);

        //Cantidad de lineas
        int Cantidadlineas = doc.html().split(System.getProperty("line.separator")).length;
        System.out.printf("\n Total de lineas: " + Cantidadlineas);

        //Parrafos
        Elements parrafos = doc.select("p"); // parrafos
        System.out.print("\n Cantidad de parrafos: " + parrafos.size());

        //Imagenes
        Elements imagenes = doc.select("img"); // imagenes
        System.out.print("\n Cantidad de imagenes: " + imagenes.size());

        //Formularios
        Elements Formularios = doc.select("form");
        System.out.print("\n Cantidad de formularios: " + Formularios.size());
        //Mostrando el tipo y el nombre

        ArrayList<Elements> inputs = new ArrayList<Elements>();

        for (Element formulario : Formularios) {
            inputs.add(formulario.getElementsByTag("input"));
        }
        int contador = 1;
        for (Elements articulos : inputs) {

            System.out.println("\nFormulario: # " + contador + ":");
            for (Element input : articulos) {
                String tipo = input.attr("type");
                String nombre = input.attr("name");
                System.out.println("Tipo: " + tipo + "  Nombre: " + nombre + "\nCampos :" + input);
            }
            contador++;

        }

    }


    }

}
