package com.NRG.vende.models;

public enum UsuarioCargoEnum {
    ADMIN("admin"),
    User("user");

    private String cargo;

    UsuarioCargoEnum(String cargo) {
        this.cargo = cargo;
    }
    public String getCargo() {
        return cargo;
    }
}
