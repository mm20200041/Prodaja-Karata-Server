/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.Racun;
import domen.StavkaRacuna;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import operacija.ApstraktnaGenerickaOperacija;
import util.EmailSender;
import util.PdfGenerator;

/**
 *
 * @author marko
 */
public class IzmeniRacunOperacija extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da zapamti racun");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Racun r = (Racun) objekat;
        broker.edit(r);
        
        
        
        String uslov = " JOIN karta ON karta.kartaID=stavkaracuna.kartaID "
                     + " JOIN sektor ON sektor.sektorID=karta.sektorID"
                     + " JOIN utakmica ON utakmica.utakmicaID=karta.utakmicaID"
                     + " JOIN hala ON hala.halaID=utakmica.halaID"
                     + " WHERE racunID="+r.getRacunID()+" ";
       
        List<StavkaRacuna> stareStavke = broker.getAll(new StavkaRacuna(), uslov);
        
        List<StavkaRacuna> noveStavke = r.getStavke();
        
        
        
        for (StavkaRacuna sr : stareStavke) {
            if(!noveStavke.contains(sr)){
                if(sr.getKarta().isIskorisceno()){
                   throw new Exception(  "Racun nije moguce izmeniti jer sadrzi vec iskoriscenu kartu." );
                }
                sr.getKarta().setProdato(false);
                sr.getKarta().setKod(null);
                broker.edit(sr.getKarta());
                broker.delete(sr);
            }
        }
        
        List<File> pdfNoveKarte = new ArrayList<>();

        
        for (StavkaRacuna sr : noveStavke) {
            sr.setRacun(r);  
            if(!stareStavke.contains(sr)){
                sr.getKarta().setProdato(true);
                sr.getKarta().setKod(UUID.randomUUID().toString());
                sr.getRacun().setRacunID(r.getRacunID());
                broker.edit(sr.getKarta());
                broker.add(sr);
                File pdf = PdfGenerator.generisiUlaznicu(sr);
                pdfNoveKarte.add(pdf);
            }
        }
        
        if (!pdfNoveKarte.isEmpty()) {
                EmailSender.posaljiMailSaPDF(
                r.getKupac().getEmail(),
                "IZMENA KUPOVINE",
                "Vaš račun je uspešno izmenjen.\n" +
                "U prilogu se nalaze nove ulaznice.\n\n" ,
                pdfNoveKarte
            );
        
        }
    }
}
