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
public class ObrisiKupcaOperacija extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Kupac)){
            throw new Exception("Sistem ne moze obrise kupca");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        broker.delete((Kupac) objekat);
    }
    
}
