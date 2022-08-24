/**
 * 
 */
package org.eej.technical.eval.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Mikel Ibiricu Alfaro
 *
 */
public class Good implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LogManager.getLogger();
	
	private String name;
	
	private double price;
	
	private Set<SalesTax> appliedTaxes;

	/**
	 * 
	 * @param name
	 * @param price
	 * @param appliedTaxes
	 */
	public Good(String name, double price, SalesTax... salesTax) {
		super();
		this.name = name;
		this.price = price;
		this.appliedTaxes = new HashSet<SalesTax>(Arrays.asList(salesTax));
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
	 * @return the price
	 */
	public double getPrice() {
		return new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the basicSalesTax
	 */
	public Collection<SalesTax> getAppliedTaxes() {
		return appliedTaxes;
	}

	/**
	 * @param basicSalesTax the basicSalesTax to set
	 */
	public void setBasicSalesTax(Collection<SalesTax> basicSalesTax) {
		this.appliedTaxes = new HashSet<SalesTax>(basicSalesTax);
	}

	/**
	 * 
	 * @return
	 */
	public double getTaxes() {
		double taxes = 0.0;
		logger.trace(this.getName());
		for(SalesTax tax : this.getAppliedTaxes()) {
			double currentTax = tax.calculateTaxes(this.getPrice());
			logger.trace("currentTax " + currentTax);
			taxes += currentTax;
			
		}
		double taxRounded = (double)Math.round(taxes * 20) / 20;
		return taxRounded;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTotal() {
		return this.getPrice() + this.getTaxes();
	}

}
