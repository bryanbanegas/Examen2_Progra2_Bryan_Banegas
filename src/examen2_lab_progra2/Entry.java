/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen2_lab_progra2;

/**
 *
 * @author adalb
 */
public class Entry {
    public Entry siguiente;
    public String username;
    public long position;

    public Entry(String codigo, long position) {
        this.username=codigo;
        this.position=position;
        this.siguiente=null;
    }

    public void setSiguiente(Entry siguiente) {
        this.siguiente=siguiente;
    }
    
    public Entry getSiguiente() {
        return siguiente;
    }

    public String getUsername() {
        return username;
    }

    public long getPosition() {
        return position;
    }
    
    
    
}
