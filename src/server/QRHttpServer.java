/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author marko
 */
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import domen.Karta;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacija.karte.ProveriKartuOperacija;
import util.HtmlTemplate;

public class QRHttpServer {
    
    private HttpServer server;


    public void pokreni() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/provera", (exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String kod = query.split("=")[1];

            ProveriKartuOperacija op = new ProveriKartuOperacija();
            try {
                    op.izvrsi(kod);
                } catch (Exception ex) {
                    Logger.getLogger(QRHttpServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                Karta k = op.getRezultat();
                
                String html;
                if(k == null){
                    html = HtmlTemplate.crveni("Kod ne postoji");
                } else if(!k.isProdato()){
                    html = HtmlTemplate.crveni("Karta nije kupljena");
                } else if(k.isIskorisceno()){
                    html = HtmlTemplate.crveni("Ulaznica je već iskorišćena");
                } else {
                    html = HtmlTemplate.zeleni(
                    "Ulaz dozvoljen",
                    "Sektor " + k.getSektor().getBroj() +
                    " · Red " + k.getRed() +
                    " · Sedište " + k.getBrojSedista());
                }

                byte[] responseBytes = html.getBytes(StandardCharsets.UTF_8); 
                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8"); 
                exchange.sendResponseHeaders(200, responseBytes.length);
                
                exchange.getResponseBody().write(html.getBytes());
                exchange.getResponseBody().close();

                }
        ));

        server.start();
        System.out.println("QR server startovan na portu 8080");
    }
    
    public void ugasi() {
        if (server != null) {
            server.stop(0);
            System.out.println("QR server zaustavljen.");
        }
    }
}

    


