package com.example.parcial.service.rabbitMQService;

import com.example.parcial.config.rabbitMQ.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void notifyMachineAssembled(String machineId) {
        String message = "Máquina ensamblada: " + machineId;
        sendMessage(message);
    }


    public void notifyBallAddedToContainer(String containerName, String bolaId) {
        String message = "Bola " + bolaId + " añadida al contenedor: " + containerName;
        sendMessage(message);
    }


    public void notifyContainerInitialized(String containerName, String containerId) {
        String message = "Contenedor inicializado: Nombre=" + containerName + ", ID=" + containerId;
        sendMessage(message);
    }

    public void notifyProductionCompleted(String factoryId, String productionName) {
        String message = "Producción completada: Fábrica ID=" + factoryId + ", Nombre=" + productionName;
        sendMessage(message);
    }


    public void notifyError(String errorMessage) {
        String message = "Error durante la producción: " + errorMessage;
        sendMessage(message);
    }


    private void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("Notificación enviada: " + message);
    }
}
