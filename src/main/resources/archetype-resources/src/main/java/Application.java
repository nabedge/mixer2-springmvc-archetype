#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ${package}.config.Mixer2EngineSingleton;
import org.mixer2.Mixer2Engine;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        System.setProperty("com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl.fastBoot","true");
        CompletableFuture<Mixer2Engine> mixer2EngineCompletableFuture
                = CompletableFuture.supplyAsync(() -> Mixer2EngineSingleton.getInstance());

        SpringApplication.run(Application.class, args);
    }
}