/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.karte;

import domen.Hala;
import domen.Karta;
import domen.Sektor;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajNeprodateKarteOperacija extends ApstraktnaGenerickaOperacija {
    List<Karta> karte;

    public List<Karta> getKarte() {
        return karte;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Karta k = (Karta) objekat;
        karte = broker.getAll(new Karta(), " JOIN sektor ON karta.sektorID=sektor.sektorID" + 
                                           " JOIN utakmica ON karta.utakmicaID=utakmica.utakmicaID" + 
                                           " JOIN hala ON hala.halaID=utakmica.halaID" +
                                           " WHERE karta.utakmicaID="+k.getUtakmica().getUtakmicaID()+
                                           " AND karta.sektorID="+k.getSektor().getSektorID()+
                                           " AND red='"+k.getRed()+"' AND prodato=0");
        
         
    }
}
