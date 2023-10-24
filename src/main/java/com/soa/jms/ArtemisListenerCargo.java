/**
 * 
 */
package com.soa.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.soa.business.BancoBusiness;
import com.soa.dto.CargoTarjeta;
import com.soa.dto.Respuesta;

/**
 * Class for receiving messages in an artemis queue.
 */
@Component
public class ArtemisListenerCargo {
    @Autowired
    private BancoBusiness business;

    @Autowired
    private JmsSender sender;

    /** Nombre de la cola de respuesta del microservicio. */
    @Value("${cargo.queue.name.out}")
    private String outQueueName;

    @JmsListener(destination = "${cargo.queue.name.in}")
    public void receive(String message) {
        System.out.println(String.format("Received message: %s",
                message));
        Gson gson = new Gson();
        CargoTarjeta cargo = gson.fromJson(message, CargoTarjeta.class);
        Respuesta respuesta = business.cargo(cargo);
        System.out.println("Resultado de consulta: "+respuesta);
        try {
            sender.sendMessage(respuesta.toString(), outQueueName);
            System.out.println(String.format("Mensaje enviado: %s", 
                    respuesta.toString()));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
