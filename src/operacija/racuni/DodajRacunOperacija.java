/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.Karta;
import domen.Kupac;
import domen.Racun;
import domen.StavkaRacuna;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import util.EmailSender;
import util.PdfGenerator;

/**
 *
 * @author marko
 */
public class DodajRacunOperacija extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da zapamti racun");
        }
       
        Racun r = (Racun) objekat;
        List<StavkaRacuna> stavke = r.getStavke();

        if (stavke.isEmpty()) {
            throw new Exception("Racun mora imati bar jednu stavku.");
        }

        for (StavkaRacuna s : stavke) {
            if (s.getKarta().isProdato()) {
                throw new Exception("Karta " + s.getKarta() + " je vec prodata.");
            }
            if (s.getKarta().getUtakmica().getUtakmicaID() != r.getUtakmica().getUtakmicaID()) {
                throw new Exception("Sve karte moraju biti za istu utakmicu.");
            }
        }
        
    }
    
    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Racun r = (Racun) objekat;

        int idRacuna = broker.addReturnKey(r);
        r.setRacunID(idRacuna);

        List<StavkaRacuna> stavke = r.getStavke();
        List<File> pdfovi = new ArrayList<>();
        
        
        for (StavkaRacuna sr : stavke) {
            String kod = UUID.randomUUID().toString();
            sr.getKarta().setKod(kod);
            sr.getKarta().setProdato(true);
            sr.setRacun(r);
            broker.add(sr);
            //broker.edit(sr.getKarta());
            
            
            try {
                broker.edit(sr.getKarta());
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
            
            
            File pdf = PdfGenerator.generisiUlaznicu(sr);
            pdfovi.add(pdf);

        }

        
        if(stavke.size()==1){
            EmailSender.posaljiMailSaPDF(
                    r.getKupac().getEmail(),
                    "USPEŠNA KUPOVINA",
                    "Uspešno ste kupili ulaznicu za utakmicu:\n"
                    + r.getUtakmica().getDomaciTim() + " - " + r.getUtakmica().getGostujuciTim(),
                    pdfovi
            );
        }
        else if(stavke.size()>1){
            EmailSender.posaljiMailSaPDF(
                    r.getKupac().getEmail(),
                    "USPEŠNA KUPOVINA",
                    "Uspešno ste kupili ulaznice za utakmicu:\n"
                    + r.getUtakmica().getDomaciTim() + " - " + r.getUtakmica().getGostujuciTim(),
                    pdfovi
            );
        }
    }

    
    
}




