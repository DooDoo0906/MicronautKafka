package micronautKafka.consumer;

import io.micronaut.runtime.Micronaut;
import io.vertx.core.json.JsonObject;
import micronautKafka.producer.Producer;

public class Application {

    public static void main(String[] args) {
        Producer client;
        JsonObject message= new JsonObject();
        message.put("a",9*10);
        message.put("b",0);
        message.put("ope",":");
        client = Micronaut.run(Application.class, args).getBean(Producer.class);
        client.sendProduct(message);
    }
}
