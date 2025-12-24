/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.Racun;
import domen.StavkaRacuna;
import domen.Utakmica;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class PretraziRacuneOperacija extends ApstraktnaGenerickaOperacija{
    List<Racun> racuni;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Racun r = (Racun) objekat;
        String uslov;
        if(r.getDatum()==null){
            uslov = " Join zaposleni on zaposleni.zaposleniID=racun.zaposleni " +
                    " JOIN utakmica ON racun.utakmica=utakmica.utakmicaID " +
                    " JOIN kupac ON racun.kupac=kupac.kupacID" +
                    " JOIN hala ON hala.halaID=utakmica.halaID" +
                    " WHERE racun.kupac="+r.getKupac().getKupacID()+" AND racun.utakmica="+r.getUtakmica().getUtakmicaID();
                
        }else{
            uslov = " Join zaposleni on zaposleni.zaposleniID=racun.zaposleni " +
                    " JOIN utakmica ON racun.utakmica=utakmica.utakmicaID " +
                    " JOIN kupac ON racun.kupac=kupac.kupacID " +
                    " JOIN hala ON hala.halaID=utakmica.halaID" 
                  + " WHERE racun.kupac="+r.getKupac().getKupacID()+" AND racun.utakmica="+r.getUtakmica().getUtakmicaID()+
                    " AND racun.datum LIKE '%"+ (new java.sql.Date(r.getDatum().getTime())) +"%'";
        }
        
        racuni = broker.getAll(new Racun() , uslov);
        System.out.println(racuni);
        
        for (Racun racun : racuni) {
            List<StavkaRacuna> stavke = broker.getAll(new StavkaRacuna(), " JOIN karta ON karta.kartaID=stavkaracuna.kartaID"
                                                                        + " JOIN sektor ON sektor.sektorID=karta.sektorID"
                                                                        + " JOIN utakmica ON utakmica.utakmicaID=karta.utakmicaID"
                                                                        + " JOIN hala ON hala.halaID=utakmica.halaID" +
                                                                          " WHERE stavkaracuna.racunID = " + racun.getRacunID());
            racun.setStavke(stavke);
            racun.setUkupnaCena();
        }

        
    }

    public List<Racun> getRacune() {
        return racuni;
    }
}
