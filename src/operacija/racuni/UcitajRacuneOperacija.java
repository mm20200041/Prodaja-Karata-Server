/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajRacuneOperacija extends ApstraktnaGenerickaOperacija{
     
    List<Racun> racuni;

    public List<Racun> getRacuni() {
        return racuni;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        String uslov = " Join zaposleni on zaposleniID=zaposleni\n" +
                       "JOIN utakmica ON utakmicaID=utakmica\n" +
                       "JOIN kupac ON kupacID=kupac " + 
                       "JOIN hala ON hala.halaID=utakmica.halaID";
        racuni = broker.getAll(new Racun(), uslov);
        
        for (Racun r : racuni) {
            String uslov2 = " JOIN karta ON karta.kartaID=stavkaracuna.kartaID"
                          + " JOIN sektor ON sektor.sektorID=karta.sektorID"
                          + " JOIN utakmica ON utakmica.utakmicaID=karta.utakmicaID"
                          + " JOIN hala ON hala.halaID=utakmica.halaID"
                          + " WHERE racunID="+r.getRacunID()+" ";
            r.setStavke(broker.getAll(new StavkaRacuna(), uslov2));
        }
        
    }
    
}
