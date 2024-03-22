/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2_lab_progra2;

import examen2_lab_progra2.HashTable.Trophy;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author adalb
 */
public class PSNUsers {
    public RandomAccessFile usersfile,psn;
    public HashTable users;
    public long position;

    public PSNUsers() {
        try{
            File file=new File("Examen");
            file.mkdir();
            position=0;
            usersfile=new RandomAccessFile("Examen/usersFile","rw");
            psn=new RandomAccessFile("Examen/trophiesFile","rw");
            users=new HashTable();
            reloadHashTable(); 
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    
    private void reloadHashTable()throws IOException{
        usersfile.seek(0);
        while(usersfile.getFilePointer()<usersfile.length()){
            String username=usersfile.readUTF();
            long pos=usersfile.readLong();
            usersfile.readInt();
            usersfile.readInt();
            usersfile.readBoolean();
            users.add(username, pos);
            position++;
        }
    }
    
    public void addUser(String username)throws IOException{
        usersfile.seek(0);
        boolean existe=false;
        while(usersfile.getFilePointer()<usersfile.length()){
            String user=usersfile.readUTF();
            usersfile.readLong();
            usersfile.readInt();
            usersfile.readInt();
            usersfile.readBoolean();
            existe=true;
        }
        usersfile.seek(usersfile.length());
        if(!existe){
            usersfile.writeUTF(username);
            usersfile.writeLong(position);
            users.add(username, position);
            usersfile.writeInt(0);
            usersfile.writeInt(0);
            usersfile.writeBoolean(true);
            JOptionPane.showMessageDialog(null, "Creo su usuario con exito.");
            position++;
        }else{
            JOptionPane.showMessageDialog(null, "Este usuario ya existe.");
        }
    }
    
    public boolean deactivateUser(String username)throws IOException{
        if(users.search(username)==-1){
            JOptionPane.showMessageDialog(null, "Este usuario no existe.");
        }else{
            if(isDeactivated(username)){
                usersfile.seek(0);
                while(usersfile.getFilePointer()<usersfile.length()){
                    String user=usersfile.readUTF();
                    usersfile.readLong();
                    usersfile.readInt();
                    usersfile.readInt();
                    if(user.equals(username)){
                        usersfile.writeBoolean(false);
                        users.remove(username);
                        return true;
                    }else{
                        usersfile.readBoolean();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Este usuario ya esta desactivado.");
            }
        }
        return false;
    }
    
    private boolean isDeactivated(String username)throws IOException{
        usersfile.seek(0);
        while(usersfile.getFilePointer()<usersfile.length()){
            String user=usersfile.readUTF();
            usersfile.readLong();
            usersfile.readInt();
            usersfile.readInt();
            if(user.equals(username)){
                return usersfile.readBoolean();
            }else{
                usersfile.readBoolean();
            }
        }
        return false;
    }
    
    public void addTrophieTo(String username, String trophyGame, String trophyName, Trophy type)throws IOException{
        boolean add=false;
        if(users.search(username)==-1){
            JOptionPane.showMessageDialog(null, "Este usuario no existe.");
        }else{
            if(isDeactivated(username)){
                usersfile.seek(0);
                while(usersfile.getFilePointer()<usersfile.length()){
                    String user=usersfile.readUTF();
                    usersfile.readLong();
                    int puntos=usersfile.readInt();
                    int cantidadTrofeos=usersfile.readInt();
                    if(user.equals(username)){
                        psn.seek(psn.length());
                        psn.writeUTF(username);
                        switch(type.getValor()){
                            case 5:
                                psn.writeUTF("PLATINO");
                                break;
                            case 3:
                                psn.writeUTF("ORO");
                                break;
                            case 2:
                                psn.writeUTF("PLATA");
                                break;
                            case 1:
                                psn.writeUTF("BRONCE");
                                break;
                        }
                        psn.writeUTF(trophyGame);
                        psn.writeUTF(trophyName);
                        Date fecha=new Date();
                        psn.writeUTF(fecha.toString());
                        add=true;
                    }
                    if(add){
                        long pos=usersfile.getFilePointer()-8;
                        usersfile.seek(pos);
                        usersfile.writeInt(puntos+type.getValor());
                        usersfile.writeInt(cantidadTrofeos+1);
                    }
                    usersfile.readBoolean();
                    add=false;
                }
            }else{
                JOptionPane.showMessageDialog(null, "Este usuario esta desactivado.");
            }
        }
    }
    
    public void playerInfo(String username)throws IOException{
        String mensaje="";
        if(users.search(username)==-1){
            mensaje="Este usuario no existe.";
        }else{
            if(isDeactivated(username)){
                usersfile.seek(0);
                while(usersfile.getFilePointer()<usersfile.length()){
                    String user=usersfile.readUTF();
                    String pos="Posicion en la lista: "+usersfile.readLong()+"-";
                    String puntos="Puntos totales: "+usersfile.readInt()+"-";
                    String cantidadTrofeos="Cantidad de trofeos: "+usersfile.readInt()+"-";
                    usersfile.readBoolean();
                    if(user.equals(username)){
                        user+=" Info: ";
                        mensaje=user+pos+puntos+cantidadTrofeos;
                    }
                }
                psn.seek(0);
                while(psn.getFilePointer()<psn.length()){
                    String usuario=psn.readUTF();
                    if(usuario.equals(username)){
                        String tipo="Tipo de trofeo: "+psn.readUTF()+"-";
                        String juego="Nombre del juego: "+psn.readUTF()+"-";
                        String trofeo="Nombre del trofeo: "+psn.readUTF()+"-";
                        String fecha="Fecha de cuando lo consiguio: "+psn.readUTF()+"-";
                        mensaje+="\n"+fecha+tipo+juego+trofeo;
                    }else{
                        psn.readUTF();
                        psn.readUTF();
                        psn.readUTF();
                        psn.readUTF();
                    }
                }
            }else{
                mensaje="Este usuario esta desactivado.";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
