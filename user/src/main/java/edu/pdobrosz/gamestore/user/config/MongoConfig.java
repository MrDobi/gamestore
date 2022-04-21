package edu.pdobrosz.gamestore.user.config;

import java.util.concurrent.TimeUnit;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

	@Bean
	@ConditionalOnProperty(name = "service.mock", havingValue = "false")
	public MongoClient mongo() {
		ConnectionString connectionString = new ConnectionString("mongodb://mongo-service:27017/user");
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.applyToSocketSettings(builder -> builder.readTimeout(1000, TimeUnit.MILLISECONDS).connectTimeout(1000,
						TimeUnit.MILLISECONDS))
				.build();

		return MongoClients.create(mongoClientSettings);
	}

	@Bean
	@ConditionalOnProperty(name = "service.mock", havingValue = "false")
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongo(), "user");
	}

}
