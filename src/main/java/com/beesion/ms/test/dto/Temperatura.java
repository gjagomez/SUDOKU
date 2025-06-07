package com.beesion.ms.test.dto;

import java.util.Objects;

public class Temperatura {

	private String ciudad;

	private int minima;

	private int maxima;

	@Override
	public String toString() {
		return "Temperatura [ciudad=" + ciudad + ", minima=" + minima + ", maxima=" + maxima + ", hashCode()="
				+ hashCode() + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Temperatura other = (Temperatura) obj;
		return Objects.equals(ciudad, other.ciudad) && maxima == other.maxima && minima == other.minima;
	}

	public int hashCode() {
		return Objects.hash(ciudad, maxima, minima);
	}

	public Temperatura() {
		super();
	}

	public Temperatura(String ciudad, int minima, int maxima) {
		super();
		this.ciudad = ciudad;
		this.minima = minima;
		this.maxima = maxima;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getMinima() {
		return minima;
	}

	public void setMinima(int minima) {
		this.minima = minima;
	}

	public int getMaxima() {
		return maxima;
	}

	public void setMaxima(int maxima) {
		this.maxima = maxima;
	}

}
