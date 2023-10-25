/**
 * 
 */
package com.soa.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.soa.dto.DatosRenta;
import com.soa.dto.Respuesta;
import com.soa.dto.Titulo;

/**
 * Class for receiving messages in an artemis queue.
 */
@Component
public class ArtemisListenerRentar {
   
    @Autowired
    private JmsSender sender;

    /** Nombre de la cola de respuesta del microservicio. */
    @Value("${catalogo.queue.name.in}")
    private String outQueueNameCatalogo;
    
    @JmsListener(destination = "${rentar.queue.name.in}")
    public void receive(String message) {
        System.out.println(String.format("Received message: %s",
                message));
        Gson gson = new Gson();
        DatosRenta data = gson.fromJson(message, DatosRenta.class);
        Titulo titulo = new Titulo(data.getTitulo(), data.getTiempo());
        System.out.println(String.format("Mensaje enviado: %s   a  %s",data.toString(), outQueueNameCatalogo));
        
        try {
            sender.sendMessage(data.toString(), outQueueNameCatalogo); //Pasa mensaje a catalogo
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
