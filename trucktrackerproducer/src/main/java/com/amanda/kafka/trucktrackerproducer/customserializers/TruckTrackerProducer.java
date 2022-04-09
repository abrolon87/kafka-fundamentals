package com.amanda.kafka.trucktrackerproducer.customserializers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;



public class TruckTrackerProducer {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.setProperty("value.serializer", "com.amanda.kafka.trucktrackerproducer.customserializers.TruckCoordinatesSerializer");

		KafkaProducer<Integer, TruckCoordinates> producer = new KafkaProducer<Integer, TruckCoordinates>(props);
		TruckCoordinates truckCoordinates = new TruckCoordinates();
		truckCoordinates.setId(10);
		truckCoordinates.setLongitude("35.5432 N");
		truckCoordinates.setLatitude("90.6543 E");
		ProducerRecord<Integer, TruckCoordinates> record = new ProducerRecord<Integer, TruckCoordinates>("TruckCSGroup", truckCoordinates.getId(), truckCoordinates);
		
		try {
			producer.send(record);
			System.out.println(record.value());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}

	}

}
