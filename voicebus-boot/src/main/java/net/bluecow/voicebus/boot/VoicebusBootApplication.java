package net.bluecow.voicebus.boot;

import net.bluecow.voicebus.core.dialogflow.DialogflowHandler;
import net.bluecow.voicebus.core.nextbus.NextbusClient;
import net.bluecow.voicebus.core.nextbus.NextbusClientFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VoicebusBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoicebusBootApplication.class, args);
	}

	@Bean
	NextbusClient nextbusClient() {
		return new NextbusClientFactory().nextbusClient();
	}

	@Bean
    DialogflowHandler dialogflowHandler() {
	    return new DialogflowHandler(nextbusClient());
    }
}
