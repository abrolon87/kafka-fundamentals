package com.amanda.kafka.trucktrackerproducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;



public class TruckTrackerProducer {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
		ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>("TruckGroup", 10, "35.5432 N, 90.6543 E");
		
		try {
			producer.send(record, new OrderCallback());
			System.out.println(record.value());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}

	}

}
