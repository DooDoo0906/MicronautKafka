package micronautKafka.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.vertx.core.json.JsonObject;

@KafkaClient
public interface Producer {
    @Topic("my-calculator")
    void sendProduct( JsonObject name);

}
