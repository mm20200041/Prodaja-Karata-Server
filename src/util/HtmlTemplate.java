/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

public class HtmlTemplate {

    public static String zeleni(String naslov, String detalji) {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head>" +
               "<meta charset='UTF-8'>" +
               "<title>Validacija ulaznice</title>" +
               "<style>" +
               "body{" +
               "margin:0;height:100vh;background:#2ecc71;" +
               "display:flex;justify-content:center;align-items:center;" +
               "font-family:Arial;color:white;}" +
               ".box{text-align:center;padding:30px;}" +
               ".icon{font-size:400px;margin-bottom:20px;}" +
               ".title{font-size:120px;font-weight:bold;}" +
               ".details{font-size:70px;margin-top:15px;}" +
               "</style>" +
               "</head>" +
               "<body>" +
               "<div class='box'>" +
               "<div class='icon'>✓︎</div>" +
               "<div class='title'>" + naslov + "</div>" +
               "<div class='details'>" + detalji + "</div>" +
               "</div>" +
               "</body>" +
               "</html>";
    }

    public static String crveni(String poruka) {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head>" +
               "<meta charset='UTF-8'>" +
               "<title>Validacija ulaznice</title>" +
               "<style>" +
               "body{" +
               "margin:0;height:100vh;background:#e74c3c;" +
               "display:flex;justify-content:center;align-items:center;" +
               "font-family:Arial;color:white;}" +
               ".box{text-align:center;padding:30px;}" +
               ".icon{font-size:400px;margin-bottom:20px;}" +
               ".title{font-size:100px;font-weight:bold;}" +
               "</style>" +
               "</head>" +
               "<body>" +
               "<div class='box'>" +
               "<div class='icon'>✖</div>" +
               "<div class='title'>" + poruka + "</div>" +
               "</div>" +
               "</body>" +
               "</html>";
    }
}


