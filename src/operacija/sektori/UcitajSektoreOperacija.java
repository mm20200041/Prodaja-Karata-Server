/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.sektori;

import domen.Hala;
import domen.Racun;
import domen.Sektor;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajSektoreOperacija extends ApstraktnaGenerickaOperacija {
    List<Sektor> sektori;

    public List<Sektor> getSektori() {
        return sektori;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Hala h = (Hala) objekat;
        sektori = broker.getAll(new Sektor(), " JOIN hala ON sektor.halaID = hala.halaID" +
                                                " WHERE sektor.halaID="+h.getHalaID());
    }
}
