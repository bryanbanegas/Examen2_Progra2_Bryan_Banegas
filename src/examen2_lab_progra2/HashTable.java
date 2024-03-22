/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2_lab_progra2;

import javax.swing.JOptionPane;

/**
 *
 * @author adalb
 */
public class HashTable {
    public Entry Lista=null;
    
    public boolean vacia(){
        if(Lista==null){
            return true;
        }
        return false;
    }
    
    public void add(String username, long pos) {
        Entry agregar=new Entry(username,pos);
        if(vacia()){
            Lista=agregar;
        }else{
            Entry lista=Lista;
            while(lista.siguiente!=null){
                lista=lista.siguiente;
            }
            lista.siguiente=agregar;
        }
    }
    
    public boolean remove(String username){
        if(!vacia()){
            if(Lista.username.equals(username)){
                Lista=Lista.siguiente;
                return true;
            }else{
                Entry lista=Lista;
                while(lista.siguiente!=null){
                    if(lista.siguiente.username.equals(username)){
                        lista.siguiente=lista.siguiente.siguiente;
                        return true;
                    }
                    lista=lista.siguiente;
                }
            }
        }
        return false;
    }
    
    public long search(String username){
        if(!vacia()){
            if(Lista.username.equals(username)){
                return Lista.position;
            }else{
                Entry lista=Lista;
                while(lista.siguiente!=null){
                    if(lista.siguiente.username.equals(username)){
                        return lista.siguiente.position;
                    }
                    lista=lista.siguiente;
                }
            }
        }
        return -1;
    }
    
    public enum Trophy{
        PLATINO(5),ORO(3),PLATA(2),BRONCE(1);
        private final int valor;

        private Trophy(int valor){
            this.valor=valor;
        }

        public int getValor(){
           return valor;
        }
    } 
}
