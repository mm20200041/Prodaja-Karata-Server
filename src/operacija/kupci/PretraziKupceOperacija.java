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
public class PretraziKupceOperacija extends ApstraktnaGenerickaOperacija{
    
    List<Kupac> kupci;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Kupac k = (Kupac) objekat;
        String uslov = " WHERE ime LIKE '%"+k.getIme()+"%' AND prezime LIKE '%"+k.getPrezime()+
                "%' AND email LIKE '%"+k.getEmail()+"%'";
        kupci = broker.getAll(new Kupac() , uslov);
        System.out.println(kupci);
    }

    public List<Kupac> getKupci() {
        return kupci;
    }

    
}
