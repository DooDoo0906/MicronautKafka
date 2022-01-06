package micronautKafka.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.vertx.core.json.JsonObject;
import micronautKafka.ultiliities.calculator.Calculator;
import micronautKafka.ultiliities.validation.Validation;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class Consumer {
    @Topic("my-calculator")
    public void receive(JsonObject message) {

        if (Validation.isNumeric(String.valueOf(message.getValue("a"))) && Validation.isNumeric(String.valueOf(message.getValue("b")))) {
            switch (Validation.validateOpe(message.getDouble("b"), message.getString("ope"))) {
                case 1:
                    double result = Calculator.operation(message.getDouble("a"), message.getDouble("b"), message.getString("ope"));
                    System.out.println(" Value: \n" + message.encodePrettily());
                    System.out.println("Result: " + result);
                    break;
                case 0:
                    System.out.println(" Value: \n" + message.encodePrettily());
                    System.out.println("You can't put 0 under the denominator");
                    break;
                case -1:
                    System.out.println(" Value: \n" + message.encodePrettily());
                    System.out.println("Please enter the right operation (+, -, x, :)");
                    break;
            }
        } else {
            System.out.println("Error");
        }
//        System.out.println("Got Product - "  );
    }
}
