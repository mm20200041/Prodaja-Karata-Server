/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.utakmice;

import domen.Kupac;
import domen.Utakmica;
import java.security.Timestamp;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class PretraziUtakmiceOperacija extends ApstraktnaGenerickaOperacija{
    List<Utakmica> utakmice;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Utakmica u = (Utakmica) objekat;
        String uslov;
        if(u.getDatum()==null){
            uslov = " JOIN hala ON hala.halaID=utakmica.halaID WHERE gostujuciTim LIKE '%"+u.getGostujuciTim()+"%' AND naziv LIKE '%"+u.getHala().getNaziv()+
                "%'";
        }else{
            System.out.println(u.getDatum().toString());
            System.out.println(new java.sql.Date(u.getDatum().getTime()));
            uslov = " JOIN hala ON hala.halaID=utakmica.halaID WHERE gostujuciTim LIKE '%"+u.getGostujuciTim()+"%' AND naziv LIKE '%"+u.getHala().getNaziv()+
                "%' AND datum LIKE '%"+ (new java.sql.Date(u.getDatum().getTime())) +"%'";
        }
        
        utakmice = broker.getAll(new Utakmica() , uslov);
        System.out.println(utakmice);
    }

    public List<Utakmica> getUtakmice() {
        System.out.println(utakmice);
        return utakmice;
    }
}
