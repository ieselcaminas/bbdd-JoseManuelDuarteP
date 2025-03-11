package Banco;

public enum Color {
    RESET("\u001B[0m"),
    VERDE("\u001B[32m"),
    ROJO("\u001B[31m"),
    CIAN("\u001B[36m"),
    AMARILLO("\u001B[33m");

    private String color;
    private Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
