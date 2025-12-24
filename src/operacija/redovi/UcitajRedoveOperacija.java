/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.redovi;

import domen.Karta;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajRedoveOperacija extends ApstraktnaGenerickaOperacija {
    List<Karta> redovi;

    public List<Karta> getRedovi() {
        return redovi;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Karta k = (Karta) objekat;
        redovi = broker.getAll(new Karta(), " JOIN sektor ON karta.sektorID=sektor.sektorID" + 
                                            " JOIN utakmica ON karta.utakmicaID=utakmica.utakmicaID" + 
                                            " JOIN hala ON hala.halaID=utakmica.halaID" +
                                            " WHERE karta.sektorID="+k.getSektor().getSektorID() +
                                            " AND karta.utakmicaID="+k.getUtakmica().getUtakmicaID());
    }
}
