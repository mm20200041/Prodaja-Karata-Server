/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Hala;
import domen.Karta;
import domen.Kupac;
import domen.Racun;
import domen.Sektor;
import domen.StavkaRacuna;
import domen.Utakmica;
import domen.Zaposleni;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import static komunikacija.Operacija.DODAJ_KUPCA;
import static komunikacija.Operacija.LOGIN;
import static komunikacija.Operacija.OBRISI_KUPCA;
import static komunikacija.Operacija.OBRISI_UTAKMICU;
import static komunikacija.Operacija.PRETRAZI_KUPCE;
import static komunikacija.Operacija.PRETRAZI_UTAKMICE;
import static komunikacija.Operacija.UCITAJ_BROJEVE_SEKTORA;
import static komunikacija.Operacija.UCITAJ_KARTE;
import static komunikacija.Operacija.UCITAJ_KARTU;
import static komunikacija.Operacija.UCITAJ_KUPCE;
import static komunikacija.Operacija.UCITAJ_NEPRODATE_KARTE;
import static komunikacija.Operacija.UCITAJ_RACUNE;
import static komunikacija.Operacija.UCITAJ_SEKTORE;
import static komunikacija.Operacija.UCITAJ_UTAKMICE;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author marko
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    Socket soket;
    Primalac primalac;
    Posiljalac posiljalac;

    public ObradaKlijentskihZahteva(Socket soket) {
        this.soket=soket;
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
    }

    
    
    @Override
    public void run() {
        while (true) { 
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Zaposleni z = (Zaposleni) zahtev.getParametar();
                        z = Controller.getInstance().pronadjiNalog(z);
                        odgovor.setOdgovor(z);
                        break;
                    case UCITAJ_KUPCE:
                        List<Kupac> kupci = Controller.getInstance().ucitajListuKupaca();
                        odgovor.setOdgovor(kupci);
                        break;    
                    case OBRISI_KUPCA:
                        try{
                            Kupac k = (Kupac) zahtev.getParametar();
                            Controller.getInstance().obrisiKupca(k);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_KUPCA:
                        try{
                            Kupac k = (Kupac) zahtev.getParametar();
                            Controller.getInstance().kreirajKupca(k);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break; 
                    case UCITAJ_RACUNE:
                        List<Racun> racuni = Controller.getInstance().ucitajListuRacuna();
                        System.out.println("KLASA OKZ ");
                        System.out.println(racuni);
                        odgovor.setOdgovor(racuni);
                        break;
                   /*case UCITAJ_STAVKE_RACUNA:
                        List<StavkaRacuna> stavkeRacuna = Controller.getInstance().ucitajStavkeRacuna((long) zahtev.getParametar());
                        System.out.println("KLASA OKZ ");
                        System.out.println(stavkeRacuna);
                        odgovor.setOdgovor(stavkeRacuna);
                        break;*/
                    case UCITAJ_UTAKMICE:
                        List<Utakmica> utakmice = Controller.getInstance().ucitajListuUtakmica();
                        odgovor.setOdgovor(utakmice);
                        break;
                    case UCITAJ_KARTE:
                        List<Karta> karte = Controller.getInstance().ucitajListuKarata();
                        odgovor.setOdgovor(karte);
                        break;      
                    case DODAJ_RACUN:
                        try{
                            Racun r = (Racun) zahtev.getParametar();
                            Controller.getInstance().kreirajRacun(r);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;   
                    case IZMENI_RACUN:
                        try{
                            Racun r = (Racun) zahtev.getParametar();
                            Controller.getInstance().izmeniRacun(r);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;    
                    case DODAJ_UTAKMICU:
                        try{
                            Utakmica u = (Utakmica) zahtev.getParametar();
                            Controller.getInstance().kreirajUtakmicu(u);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break; 
                    case OBRISI_UTAKMICU:
                        try{
                            Utakmica u = (Utakmica) zahtev.getParametar();
                            Controller.getInstance().obrisiUtakmicu(u);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;    
                    case PRETRAZI_KUPCE:
                        try{
                            Kupac k = (Kupac) zahtev.getParametar();
                            List<Kupac> lista = Controller.getInstance().nadjiKupce(k);
                            odgovor.setOdgovor(lista);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;        
                    case PRETRAZI_UTAKMICE:
                        try{
                            Utakmica u = (Utakmica) zahtev.getParametar();
                            List<Utakmica> lista = Controller.getInstance().nadjiUtakmice(u);
                            odgovor.setOdgovor(lista);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;     
                    case PRETRAZI_RACUNE:
                        try{
                            Racun r = (Racun) zahtev.getParametar();
                            List<Racun> lista = Controller.getInstance().nadjiRacune(r);
                            odgovor.setOdgovor(lista);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;  
                    case UCITAJ_HALE:
                        List<Hala> hale = Controller.getInstance().ucitajListuHala();
                        odgovor.setOdgovor(hale);
                        break;
                    case UCITAJ_SEKTORE:
                        Hala h = (Hala) zahtev.getParametar();
                        List<Sektor> sektori = Controller.getInstance().ucitajListuSektora(h);
                        odgovor.setOdgovor(sektori);
                        break;       
                    case UCITAJ_NEPRODATE_KARTE:
                        Karta k = (Karta) zahtev.getParametar();
                        List<Karta> neprodateKarte = Controller.getInstance().ucitajNeprodateKarte(k);
                        odgovor.setOdgovor(neprodateKarte);
                        break;   
                    case UCITAJ_KARTU:
                        Karta k2 = (Karta) zahtev.getParametar();
                        Karta karta = Controller.getInstance().ucitajKartu(k2);
                        odgovor.setOdgovor(karta);
                        break;    
                    case UCITAJ_REDOVE:
                        Karta k3 = (Karta) zahtev.getParametar();
                        List<Karta> redovi = Controller.getInstance().ucitajRedove(k3);
                        odgovor.setOdgovor(redovi);
                        break;   
                    case UCITAJ_BROJEVE_SEKTORA:
                        Sektor s = (Sektor) zahtev.getParametar();
                        List<Sektor> brojeviSektora = Controller.getInstance().ucitajBrojeveSektora(s);
                        odgovor.setOdgovor(brojeviSektora);
                        break;    
                    case UCITAJ_SEKTOR:
                        Sektor se = (Sektor) zahtev.getParametar();
                        Sektor sektor = Controller.getInstance().ucitajSektor(se);
                        odgovor.setOdgovor(sektor);
                        break;      
                    default:
                        System.out.println("GRESKA, TA OPERACIJA NE POSTOJI");;
                }
                posiljalac.posalji(odgovor);
            }catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
