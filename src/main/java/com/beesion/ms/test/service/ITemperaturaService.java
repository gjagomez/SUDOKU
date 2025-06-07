package com.beesion.ms.test.service;

import java.util.List;

import com.beesion.ms.test.dto.Temperatura;

public interface ITemperaturaService {

	void addTemperatura(Temperatura t);
	
	List<Temperatura> obtenerTemperaturas();
	
	boolean isEmpty();
	
	int maxima();
	
}
