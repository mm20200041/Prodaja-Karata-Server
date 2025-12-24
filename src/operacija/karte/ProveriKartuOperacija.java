/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.karte;

import domen.ApstraktniDomenskiObjekat;
import domen.Karta;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class ProveriKartuOperacija extends ApstraktnaGenerickaOperacija {

    private Karta rezultat;

    public Karta getRezultat() {
        return rezultat;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param) throws Exception {
        String kod = (String) param;

        List<Karta> lista = broker.getAll(new Karta(), " JOIN sektor ON karta.sektorID=sektor.sektorID" + 
                                                       " JOIN utakmica ON karta.utakmicaID=utakmica.utakmicaID" + 
                                                       " JOIN hala ON hala.halaID=utakmica.halaID" +
                                                       " WHERE kod='" + kod + "'");
        
        if(lista.isEmpty()){
            rezultat = null;
            return;
        }

        Karta k = (Karta) lista.get(0);

        if(!k.isProdato() || k.isIskorisceno()){
            rezultat = k;
            return;
        }
 
        k.setIskorisceno(true);
        broker.edit(k);
        
        k.setIskorisceno(false);
        rezultat = k;
        
    }
}
