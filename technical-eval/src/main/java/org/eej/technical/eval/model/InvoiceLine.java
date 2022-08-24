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
public class InvoiceLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Good good;
	
	private int amount;

	public InvoiceLine(Good good, int amount) {
		super();
		this.good = good;
		this.amount = amount;
	}

	/**
	 * @return the good
	 */
	public Good getGood() {
		return good;
	}

	/**
	 * @param good the good to set
	 */
	public void setGood(Good good) {
		this.good = good;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTaxes() {
		return new BigDecimal(this.amount * this.getGood().getTaxes()).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTotal() {
		return new BigDecimal((this.amount * this.getGood().getPrice()) + this.getTaxes()).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
