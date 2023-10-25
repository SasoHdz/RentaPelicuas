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
public class ArtemisListenerStatus {
   
    @Autowired
    private JmsSender sender;

    /** Nombre de la cola de respuesta del microservicio. */
    @Value("${status.queue.name.out}")
    private String outQueueNameCatalogo;
    
    @JmsListener(destination = "${status.queue.name.in}")
    public void receive(String message) {
        System.out.println(String.format("Received message: %s",
                message));
        Gson gson = new Gson();
        Respuesta resp = gson.fromJson(message, Respuesta.class);
        
        try {
            sender.sendMessage(resp.toString(), outQueueNameCatalogo); //Pasa mensaje a catalogo
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
