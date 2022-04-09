package com.amanda.kafka.trucktrackerproducer.customserializers;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TruckCoordinatesSerializer implements Serializer<TruckCoordinates> {

	@Override
	public byte[] serialize(String topic, TruckCoordinates truckCoordinates) {
		byte[] response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			response = objectMapper.writeValueAsString(truckCoordinates).getBytes();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}

}
