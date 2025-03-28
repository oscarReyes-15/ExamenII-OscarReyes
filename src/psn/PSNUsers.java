/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psn;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;

/**
 *  Total: 55% - 13.75 pts
 * @author LENOVO
 */
public class PSNUsers {
    /**para gestionar el archivo psn
     */
    RandomAccessFile psn;   
    
    /**utilizada para el control de usuarios 
     * (todas las búsquedas se realizarán en esta estructura en memoria, 
     *   en lugar de acceder directamente al archivo, para mejorar la eficiencia).
     */
    HashTable users;     
    
    /**
     String de info
     */
    public String info;

    public PSNUsers() throws IOException {     
        File file = new File("archivo.psn");
        
        // si el archivo no existe, pues toca CREARLO
        if (!file.exists()) {
            file.createNewFile();
        }
        
        psn = new RandomAccessFile (file.getPath(), "rw");
        users = new HashTable ();
        
        reloadHashTable (); // (5%)???
    }
    
    /** Carga la lista enlazada con todos los registros NO BORRADOS del archivo. 
     *  Se extrae el username y la pos al leer cada registro. (10%) (como que solamente NO BORRADOS? Como se cargarian los borrados?)
     */
    private void reloadHashTable() throws IOException {
        psn.seek(0);
        
        while (psn.getFilePointer() < psn.length()) {
            long pos = psn.getFilePointer();
            String name = psn.readUTF();
            psn.skipBytes(4);
            int trof = psn.readInt();
            if (psn.readBoolean()) {
                users.add(name, pos);
            }
            
            for (int i = 0; i < trof; i++) {
                psn.readInt();
                psn.readLong();
                psn.readUTF();
                psn.readUTF();
            }
        }
    }
    
    /**
     *  - Agrega un nuevo registro al archivo si el username es único.
        - Inicializa el usuario con 0 puntos y 0 trofeos.
        - Se considera el usuario como "activo".
        - También se agrega a la HashTable. (10%)
     */
    void addUser(String username) throws IOException{
        if (users.search(username) == -1) {
            psn.seek(psn.length());

            psn.writeUTF(username);
            psn.writeInt(0);
            psn.writeInt(0);
            psn.writeBoolean(true);
        }
        reloadHashTable();
    }
    
    /**
     * Busca el usuario y, si se encuentra, 
     * se marca como inactivo en el archivo y se elimina de la HashTable. (10%)
     */
    void deactivateUser(String username) throws IOException {
        long pos = users.search(username);
        
        if(pos != -1) {
            psn.seek(pos);
            psn.readUTF();
            psn.skipBytes(8);
            psn.writeBoolean(false);
        }
    }
    
    /**
     * Busca el usuario y, si existe, le agrega un nuevo trofeo en el archivo psn.

        Formato de almacenamiento del trofeo:
            username
            tipo del trofeo
            fecha en que se ganó
            nombre del juego
            nombre del trofeo
                        
        También se actualizan el contador de trofeos y el puntaje acumulado. (15%)
     */
    void addTrophieTo(String username, String trophyGame, String trophyName, Trophy type) throws IOException {
        File file = new File("archivo2.psn");
        File fileV = new File ("archivo.psn") ;
        
        if (users.search(username) == -1) {
            return;
        }
        
        if (file.createNewFile()) {
            RandomAccessFile RAF = new RandomAccessFile (file.getPath(), "rw");
            
            psn.seek(0);
            while (psn.getFilePointer() < psn.length()) {
                int trofeos;
                String USER;

                RAF.writeUTF((USER = psn.readUTF()));
                RAF.writeInt(psn.readInt());
                RAF.writeInt((trofeos = psn.readInt()));
                RAF.writeBoolean(psn.readBoolean());

                if (username.equals(USER)){
                    trofeos += 1;
                }

                for (int i = 0; i < trofeos; i++) {
                    
                    RAF.writeInt(psn.readInt());
                    RAF.writeLong(psn.readLong());
                    RAF.writeUTF(psn.readUTF());
                    RAF.writeUTF(psn.readUTF());  

                    if (username.equals(USER)) {
                        RAF.writeInt(type.puntos);
                        RAF.writeLong(new Date().getTime());
                        RAF.writeUTF(trophyGame);
                        RAF.writeUTF(trophyName);
                    }
                }
                
            }
            try {
                
                if (Files.exists(fileV.toPath())) {
                    Files.delete(fileV.toPath());
                }

                Files.move(file.toPath(), fileV.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e) {
                System.out.println("no funciono :(");
            }
            reloadHashTable();
        }
                
        
    }
    
    /** Busca el usuario y, si lo encuentra, imprime todos sus datos.
        Luego, imprime sus trofeos en el formato: FECHA – TIPO - JUEGO – DESCRIPCIÓN. (5%)
     */
    void playerInfo(String username) throws IOException {
        info = "";
        
        long pos = users.search(username);
        if (pos != -1) {
            psn.seek(pos);
            info += psn.readUTF() + "\n";
            info += "Puntos: " + psn.readInt() + "\n";
            int trofeos = psn.readInt();
            info += "Trofeos: " + trofeos + "\n";
            info += "///////////////////////////////";
            System.out.println(trofeos);
            
            for (int i = 0; i < trofeos; i++) {
                String trophy = "";
                switch (psn.readInt()) {
                    case 5-> {
                        trophy = Trophy.PLATINO.name();
                    }
                    case 3 -> {
                        trophy = Trophy.ORO.name();
                    }
                    case 2 -> {
                        trophy = Trophy.PLATA.name();
                    }
                    case 1 -> {
                        trophy = Trophy.BRONCE.name();
                    }
                }
                
                info += "Tipo de trofeo: " +  trophy + "\n";
                info += "Fecha (en milli)" + psn.readLong() + "\n";
                info += "Nombre de Juego: " + psn.readUTF() + "\n";
                info += "Nombre de Trofeo: " + psn.readUTF() + "\n";
            }
        }
    }
}
