package com.example.projet;

public class Faculte {

    String identifiant;
    String desc;
    String numTel;
    String mail;
    String coords;

    public Faculte(String id, String desc, String numTel, String mail, String coords){
        this.identifiant = id;
        this.desc = desc;
        this.numTel = numTel;
        this.mail = mail;
        this.coords = coords;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getNumTel() {
        return this.numTel;
    }

    public String getMail() {
        return this.mail;
    }

    public String getCoords() {
        return this.coords;
    }
}
