/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;


import domen.Zaposleni;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Zaposleni zaposleni;

    public Zaposleni getZaposleni() {
        return zaposleni;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Zaposleni)){
            throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception{
        List<Zaposleni> sviZaposleni = broker.getAll((Zaposleni) objekat, null);
        System.out.println("KLASA LoginOperacija "+sviZaposleni);
        for (Zaposleni z : sviZaposleni) {
            if(z.equals((Zaposleni)objekat)){
                zaposleni = z;
                return;
            }
        }
        zaposleni = null;
    }
    
}
