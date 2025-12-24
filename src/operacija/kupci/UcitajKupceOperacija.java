/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import domen.Kupac;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajKupceOperacija extends ApstraktnaGenerickaOperacija {

    List<Kupac> kupci;

    public List<Kupac> getKupci() {
        return kupci;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        kupci = broker.getAll(new Kupac(), "");
    }
    
}
