/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.utakmice;


import domen.Karta;
import domen.Sektor;
import domen.Utakmica;
import java.util.Date;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class DodajUtakmiceOperacija extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Utakmica)){
            throw new Exception("Sistem ne moze da zapamti utakmicu");
        }
        Utakmica u = (Utakmica) objekat;
        if(u.getGostujuciTim()==null || u.getGostujuciTim().isEmpty() ){
            throw new Exception("GRESKA, gostujuci tim nije unet");
        }
        if(u.getHala()==null ){
            throw new Exception("GRESKA, hala nije uneta");
        }
        if(u.getDatum()==null ){
            throw new Exception("GRESKA, datum nije unet");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Utakmica u = (Utakmica) objekat;

        
        int utakmicaID = broker.addReturnKey(u);
        u.setUtakmicaID(utakmicaID);

        
        List<Karta> postojeceKarte = broker.getAll(
            new Karta(),
            " WHERE utakmicaID = " + utakmicaID
        );

        if (!postojeceKarte.isEmpty()) {
            throw new Exception(
                "Karte za ovu utakmicu već postoje. Duplo generisanje nije dozvoljeno."
            );
        }

        
        List<Sektor> sektori = broker.getAll(
            new Sektor(), 
            " JOIN hala ON sektor.halaID = hala.halaID" +    
            " WHERE sektor.halaID = " + u.getHala().getHalaID()
        );

        if (sektori.isEmpty()) {
            throw new Exception("Ne postoje sektori za izabranu halu.");
        }

        
        for (Sektor s : sektori) {
            if(s.getSektorID()>=88 && s.getSektorID()<=115){
                for (char red = 'A'; red <= 'T'; red++) {
                    for (int sediste = 1; sediste <= 4; sediste++) {

                        Karta k = new Karta();
                        k.setUtakmica(u);
                        k.setSektor(s);
                        k.setRed(String.valueOf(red));
                        k.setBrojSedista(sediste);

                        k.setProdato(false);
                        k.setIskorisceno(false);
                        k.setKod(null);

                        broker.add(k);
                    }
                }
            }else{
                for (char red = 'A'; red <= 'O'; red++) {
                    for (int sediste = 1; sediste <= 5; sediste++) {

                        Karta k = new Karta();
                        k.setUtakmica(u);
                        k.setSektor(s);
                        k.setRed(String.valueOf(red));
                        k.setBrojSedista(sediste);

                        k.setProdato(false);
                        k.setIskorisceno(false);
                        k.setKod(null);

                        broker.add(k);
                    }
                }
            }
        }
    }
}
