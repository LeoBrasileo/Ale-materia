package com.leobrasileo.tp3ertrimestre;

public class ObjetoMateria {
    private String nombreMateria;
    private int nota1;
    private int nota2;
    private int nota3;
    private boolean visNot1;
    private boolean visNot2;
    private boolean visNot3;

    public ObjetoMateria(String nombreMateria, int nota1, int nota2, int nota3, boolean visNot1, boolean visNot2, boolean visNot3) {
        this.nombreMateria = nombreMateria;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.visNot1 = visNot1;
        this.visNot2 = visNot2;
        this.visNot3 = visNot3;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public boolean isVisNot1() {
        return visNot1;
    }

    public void setVisNot1(boolean visNot1) {
        this.visNot1 = visNot1;
    }

    public boolean isVisNot2() {
        return visNot2;
    }

    public void setVisNot2(boolean visNot2) {
        this.visNot2 = visNot2;
    }

    public boolean isVisNot3() {
        return visNot3;
    }

    public void setVisNot3(boolean visNot3) {
        this.visNot3 = visNot3;
    }
}
