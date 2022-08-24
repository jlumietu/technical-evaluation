/**
 * 
 */
package org.eej.technical.eval.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Mikel Ibiricu Alfaro
 *
 */
public enum SalesTax implements Serializable {
	
	BASIC("basic", 10.0),
	EXEMPT("exempt", 0.0),
	IMPORTED("imported", 5.0);

	private String name;
	private double rate;

	SalesTax(String name, double rate) {
		this.name = name;
		this.rate = rate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	double calculateTaxes(double price) {
		double tax = price * this.getRate() / 100;
		return new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
