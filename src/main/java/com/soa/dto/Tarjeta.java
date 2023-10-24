package com.soa.dto;

import com.google.gson.Gson;

/**
 * Clase que modela la informacion de un usuario.
 */
public class Tarjeta {
    
    private String noTC;
    private String cvv;
    private String fechaExp;
    private Float saldo;
    
    public String getNoTc() {
        return noTC;
    }
    public void setNoTc(String noTc) {
        this.noTC = noTc;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public String getFechaExp() {
        return fechaExp;
    }
    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }
   
    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
    
    public Float getSaldo() {
        return saldo;
    }
    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
