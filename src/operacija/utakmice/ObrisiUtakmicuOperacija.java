/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.utakmice;


import domen.Utakmica;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class ObrisiUtakmicuOperacija extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Utakmica)){
            throw new Exception("Sistem ne moze obrise utakmicu");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        broker.delete((Utakmica) objekat);
    }
}
