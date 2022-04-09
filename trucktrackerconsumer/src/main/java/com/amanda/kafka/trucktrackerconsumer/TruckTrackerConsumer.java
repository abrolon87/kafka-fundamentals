package com.amanda.kafka.trucktrackerconsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class TruckTrackerConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("group.id", "TruckGroup");
		
		KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);
		
		consumer.subscribe(Collections.singletonList("TruckGroup"));
		ConsumerRecords<Integer, String> trucks = consumer.poll(Duration.ofSeconds(20));
	    for (ConsumerRecord<Integer, String> truck : trucks) {
			System.out.println("Truck Id#: " + truck.key());
			System.out.println("Current Location: " + truck.value());
		}
	    consumer.close();
	}

}
