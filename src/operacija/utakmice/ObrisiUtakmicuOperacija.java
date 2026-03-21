/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.utakmice;


import domen.Karta;
import domen.Utakmica;
import java.util.List;
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
        Utakmica u = (Utakmica) objekat;
        
        List<Karta> sveKarte = broker.getAll(new Karta(), " JOIN sektor ON karta.sektorID=sektor.sektorID" + 
                                                          " JOIN utakmica ON karta.utakmicaID=utakmica.utakmicaID" + 
                                                          " JOIN hala ON hala.halaID=utakmica.halaID" + 
                                                          " WHERE karta.utakmicaID="+u.getUtakmicaID());
        for (Karta karta : sveKarte) {
            broker.delete(karta);
        }
        
        broker.delete((Utakmica) objekat);
    }
}
