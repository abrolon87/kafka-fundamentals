package com.amanda.kafka.trucktrackerconsumer.customdeserializers;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

public class TruckTrackerConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", IntegerDeserializer.class.getName());
		props.setProperty("value.deserializer", TruckCoordinatesDeserializer.class.getName());
		props.setProperty("group.id", "TruckGroup");
		
		KafkaConsumer<Integer, TruckCoordinates> consumer = new KafkaConsumer<>(props);
		
		consumer.subscribe(Collections.singletonList("TruckCSGroup"));
		ConsumerRecords<Integer, TruckCoordinates> records = consumer.poll(Duration.ofSeconds(20));
	    for (ConsumerRecord<Integer, TruckCoordinates> record : records) {
			Integer truckId = record.key();
			TruckCoordinates truckCoordinates = record.value();
			
		    System.out.println("Truck ID: " + truckId);
		    System.out.println("Truck Coordinates: ");
		    System.out.println("- Longitude: " + truckCoordinates.getLongitude());
		    System.out.println("- Latitude: " + truckCoordinates.getLatitude());
		}
	    consumer.close();
	}

}
