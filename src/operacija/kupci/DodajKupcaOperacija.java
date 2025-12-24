/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import domen.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class DodajKupcaOperacija extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Kupac)){
            throw new Exception("Sistem ne moze da zapamti kupca");
        }
        Kupac k = (Kupac) objekat;
        
        if(k.getIme()==null || k.getIme().isEmpty() || !k.getIme().matches("[a-zA-Z]+")){
            throw new Exception("GRESKA, ime nije uneto");
        }
        if(k.getPrezime()==null || k.getPrezime().isEmpty() || !k.getPrezime().matches("[a-zA-Z]+")){
            throw new Exception("GRESKA, prezime nije uneto");
        }
        
        if (!k.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new Exception("GRESKA, neispravna email adresa: " + k.getEmail());
        }       

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        broker.add((Kupac) objekat);
    }
    
}
// 25.11.2025 17:00:00