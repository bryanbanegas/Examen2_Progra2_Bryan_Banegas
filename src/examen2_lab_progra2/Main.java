/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2_lab_progra2;

import examen2_lab_progra2.HashTable.Trophy;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author adalb
 */
public class Main extends JFrame {
    public JFrame frame=new JFrame();
    public PSNUsers funcion=new PSNUsers();
    public String username,juego,trofeo;
    public Main(){
        frame.setBounds(100,100,590,430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton addUser=new JButton("Aregar Usuario");
        addUser.setBounds(100,100,150,50); 
        addUser.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                try{
                    username=JOptionPane.showInputDialog(null, "Ingrese un usuario para crear.");
                    funcion.addUser(username);
                }catch(IOException e){
                    
                }
            }
        });
        
        frame.getContentPane().add(addUser);
        JButton deactivateUser=new JButton("Desactivar Usuario");
        deactivateUser.setBounds(100,200,150,50); 
        deactivateUser.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                try{
                    username=JOptionPane.showInputDialog(null, "Ingrese un usuario para desactivar.");
                    if(funcion.deactivateUser(username)){
                        JOptionPane.showMessageDialog(null, "Desactivo esta cuenta con exito.");
                    }
                }catch(IOException e){
                    
                }
            }
        });
        
        frame.getContentPane().add(deactivateUser);
        JButton addTrophieTo=new JButton("Aregar trofeo");
        addTrophieTo.setBounds(100,300,150,50); 
        addTrophieTo.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                try{
                    Object[] opciones={"PLATINO","ORO","PLATA","BRONCE"};
                    int error=0;
                    while(error==0){
                    try{
                        username=JOptionPane.showInputDialog(null, "Ingrese un usuario para agregar trofeo.");
                        juego=JOptionPane.showInputDialog(null, "Ingrese de cual juego es el trofeo.");
                        trofeo=JOptionPane.showInputDialog(null, "Ingrese el nombre del trofeo.");
                        int seleccion=JOptionPane.showOptionDialog(null,
                        "Elige el tipo de trofeo:",
                        "Elegir una opciOn",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,                    
                        opciones[1]); 
                        if(opciones[seleccion].equals("PLATINO")){
                            error=1;
                            funcion.addTrophieTo(username, juego, trofeo, Trophy.PLATINO);
                        }else if(opciones[seleccion].equals("ORO")){
                            error=1;
                            funcion.addTrophieTo(username, juego, trofeo, Trophy.ORO);
                        }else if(opciones[seleccion].equals("PLATA")){
                            error=1;
                            funcion.addTrophieTo(username, juego, trofeo, Trophy.PLATA);
                        }else if(opciones[seleccion].equals("BRONCE")){
                            error=1;
                            funcion.addTrophieTo(username, juego, trofeo, Trophy.BRONCE);
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                                error=0;
                    }
                    }
                }catch(IOException e){
                    
                }
            }
        });
        frame.getContentPane().add(addTrophieTo);
        
        JButton playerInfo=new JButton("Ver info");
        playerInfo.setBounds(100,400,150,50); 
        playerInfo.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                try{
                    username=JOptionPane.showInputDialog(null, "Ingrese un usuario para ver su info.");
                    funcion.playerInfo(username);
                }catch(IOException e){
                    
                }
            }
        });
        frame.getContentPane().add(playerInfo);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try{
                    Main main=new Main();
                    main.frame.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
