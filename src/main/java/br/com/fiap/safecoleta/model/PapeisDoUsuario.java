package br.com.fiap.safecoleta.model;

public enum PapeisDoUsuario {

    ADMIN("admin"),
    USER("user");

    private String role;

    PapeisDoUsuario(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
