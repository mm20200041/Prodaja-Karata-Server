/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.hale;

import domen.Hala;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajHaleOperacija extends ApstraktnaGenerickaOperacija {
    List<Hala> hale;

    public List<Hala> getHale() {
        return hale;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        hale = broker.getAll(new Hala(), "");
    }
}
