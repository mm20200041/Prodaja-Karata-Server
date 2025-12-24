/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.utakmice;


import domen.Utakmica;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author marko
 */
public class UcitajUtakmiceOperacija extends ApstraktnaGenerickaOperacija{
    List<Utakmica> utakmice;

    public List<Utakmica> getUtakmice() {
        return utakmice;
    }
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        utakmice = broker.getAll(new Utakmica(), " JOIN hala ON utakmica.halaID = hala.halaID");
    }
}
