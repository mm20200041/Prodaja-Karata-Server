/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.sektori;

import domen.Hala;
import domen.Sektor;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajBrojeveSektoraOperacija extends ApstraktnaGenerickaOperacija {
    List<Sektor> brojeviSektora;

    public List<Sektor> getBrojeviSektora() {
        return brojeviSektora;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Sektor s = (Sektor) objekat;
        brojeviSektora = broker.getAll(new Sektor(), " JOIN hala ON sektor.halaID = hala.halaID" +
                                                     " WHERE sektor.naziv='" + s.getNaziv() + "'");
    }
}
