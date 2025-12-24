/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Hala;
import domen.Karta;
import domen.Kupac;
import domen.Racun;
import domen.Sektor;
import domen.StavkaRacuna;
import domen.Utakmica;
import domen.Zaposleni;
import java.util.List;
import operacija.hale.UcitajHaleOperacija;
import operacija.karte.UcitajKarteOperacija;
import operacija.karte.UcitajKartuOperacija;
import operacija.karte.UcitajNeprodateKarteOperacija;
import operacija.kupci.DodajKupcaOperacija;
import operacija.kupci.ObrisiKupcaOperacija;
import operacija.kupci.PretraziKupceOperacija;
import operacija.login.LoginOperacija;
import operacija.kupci.UcitajKupceOperacija;
import operacija.racuni.DodajRacunOperacija;
import operacija.racuni.IzmeniRacunOperacija;
import operacija.racuni.PretraziRacuneOperacija;
import operacija.racuni.UcitajRacuneOperacija;
import operacija.redovi.UcitajRedoveOperacija;
import operacija.sektori.UcitajBrojeveSektoraOperacija;
import operacija.sektori.UcitajSektorOperacija;
import operacija.sektori.UcitajSektoreOperacija;
import operacija.utakmice.DodajUtakmiceOperacija;
import operacija.utakmice.ObrisiUtakmicuOperacija;
import operacija.utakmice.PretraziUtakmiceOperacija;
import operacija.utakmice.UcitajUtakmiceOperacija;

/**
 *
 * @author marko
 */
public class Controller {
    
    private static Controller instance;

    public Controller() {
    }
    
    public static Controller getInstance(){
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    public Zaposleni pronadjiNalog(Zaposleni z) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(z);
        System.out.println("KLASA CONTROLLER: "+operacija.getZaposleni());
        return operacija.getZaposleni();
    }

    public List<Kupac> ucitajListuKupaca() throws Exception {
        UcitajKupceOperacija operacija = new UcitajKupceOperacija();
        operacija.izvrsi(null);
        System.out.println("KLASA CONTROLLER: "+operacija.getKupci());
        return operacija.getKupci();
    }

    public void obrisiKupca(Kupac k) throws Exception {
        ObrisiKupcaOperacija operacija = new ObrisiKupcaOperacija();
        operacija.izvrsi(k);
    }

    public void kreirajKupca(Kupac k) throws Exception {
        DodajKupcaOperacija operacija = new DodajKupcaOperacija();
        operacija.izvrsi(k);
    }

    public List<Racun> ucitajListuRacuna() throws Exception {
        UcitajRacuneOperacija operacija = new UcitajRacuneOperacija();
        operacija.izvrsi(null);
        System.out.println("KLASA CONTROLLER: "+operacija.getRacuni());
        return operacija.getRacuni();
    }

    /*public List<StavkaRacuna> ucitajStavkeRacuna(long id) throws Exception {
        UcitajStavkeRacunaOperacija operacija = new UcitajStavkeRacunaOperacija();
        operacija.izvrsi(id);
        System.out.println("KLASA CONTROLLER: "+operacija.getStavkeRacuna());
        return operacija.getStavkeRacuna();
    }*/

    public List<Utakmica> ucitajListuUtakmica() throws Exception {
        UcitajUtakmiceOperacija operacija = new UcitajUtakmiceOperacija();
        operacija.izvrsi(null);
        System.out.println("KLASA CONTROLLER: "+operacija.getUtakmice());
        return operacija.getUtakmice();
    }

    public List<Karta> ucitajListuKarata() throws Exception {
        UcitajKarteOperacija operacija = new UcitajKarteOperacija();
        operacija.izvrsi(null);
        System.out.println("KLASA CONTROLLER: "+operacija.getKarte());
        return operacija.getKarte();
    }

    public void kreirajRacun(Racun r) throws Exception {
        DodajRacunOperacija operacija = new DodajRacunOperacija();
        operacija.izvrsi(r);
    }

    public void izmeniRacun(Racun r) throws Exception {
        IzmeniRacunOperacija operacija = new IzmeniRacunOperacija();
        operacija.izvrsi(r);
    }

    public void kreirajUtakmicu(Utakmica u) throws Exception {
        DodajUtakmiceOperacija operacija = new DodajUtakmiceOperacija();
        operacija.izvrsi(u);
    }

    public void obrisiUtakmicu(Utakmica u) throws Exception {
        ObrisiUtakmicuOperacija operacija = new ObrisiUtakmicuOperacija();
        operacija.izvrsi(u);
    }

    

    public List<Kupac> nadjiKupce(Kupac k) throws Exception {
        PretraziKupceOperacija operacija = new PretraziKupceOperacija();
        operacija.izvrsi(k);
        return operacija.getKupci();
    }

    public List<Utakmica> nadjiUtakmice(Utakmica u) throws Exception {
        PretraziUtakmiceOperacija operacija = new PretraziUtakmiceOperacija();
        operacija.izvrsi(u);
        return operacija.getUtakmice();
    }

    public List<Racun> nadjiRacune(Racun r) throws Exception {
        PretraziRacuneOperacija operacija = new PretraziRacuneOperacija();
        operacija.izvrsi(r);
        return operacija.getRacune();
    }

    public List<Hala> ucitajListuHala() throws Exception {
        UcitajHaleOperacija operacija = new UcitajHaleOperacija();
        operacija.izvrsi(null);
        System.out.println("KLASA CONTROLLER: "+operacija.getHale());
        return operacija.getHale();
    }

    public List<Sektor> ucitajListuSektora(Hala h) throws Exception {
        UcitajSektoreOperacija operacija = new UcitajSektoreOperacija();
        operacija.izvrsi(h);
        System.out.println("KLASA CONTROLLER: "+operacija.getSektori());
        return operacija.getSektori();
    }

    public List<Karta> ucitajNeprodateKarte(Karta k) throws Exception {
        UcitajNeprodateKarteOperacija operacija = new UcitajNeprodateKarteOperacija();
        operacija.izvrsi(k);
        System.out.println("KLASA CONTROLLER: "+operacija.getKarte());
        return operacija.getKarte();
    }

    public Karta ucitajKartu(Karta k2) throws Exception {
        UcitajKartuOperacija operacija = new UcitajKartuOperacija();
        operacija.izvrsi(k2);
        System.out.println("KLASA CONTROLLER: "+operacija.getKarta());
        return operacija.getKarta();
    }

    public List<Karta> ucitajRedove(Karta k) throws Exception {
        UcitajRedoveOperacija operacija = new UcitajRedoveOperacija();
        operacija.izvrsi(k);
        System.out.println("KLASA CONTROLLER: "+operacija.getRedovi());
        return operacija.getRedovi();
    }

    public List<Sektor> ucitajBrojeveSektora(Sektor s) throws Exception {
        UcitajBrojeveSektoraOperacija operacija = new UcitajBrojeveSektoraOperacija();
        operacija.izvrsi(s);
        System.out.println("KLASA CONTROLLER: "+operacija.getBrojeviSektora());
        return operacija.getBrojeviSektora();
    }

    public Sektor ucitajSektor(Sektor s) throws Exception {
        UcitajSektorOperacija operacija = new UcitajSektorOperacija();
        operacija.izvrsi(s);
        System.out.println("KLASA CONTROLLER: "+operacija.getSektor());
        return operacija.getSektor();
    }
}
