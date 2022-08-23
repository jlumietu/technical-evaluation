/**
 * 
 */
package org.eej.technical.eval.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author DOIBALMI
 *
 */
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LogManager.getLogger();
	
	private List<InvoiceLine> lines;
	
	/**
	 * 
	 * @param lines
	 */
	public Invoice(List<InvoiceLine> lines) {
		super();
		this.lines = lines;
	}

	/**
	 * @return the lines
	 */
	public List<InvoiceLine> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(List<InvoiceLine> lines) {
		this.lines = lines;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getSalesTaxes () {
		double taxes = 0.0;
		for(InvoiceLine line : lines) {
			double currentLineTaxes = line.getTaxes();
			logger.trace("currentLineTaxes : " + currentLineTaxes);
			taxes += currentLineTaxes;
		}
		return new BigDecimal(taxes).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTotal () {
		double total = 0.0;
		for(InvoiceLine line : lines) {
			total += line.getTotal();
		}
		return total;
	}
	
	public String getInfo() {
		StringBuilder sb = new StringBuilder("");
		for(InvoiceLine il : this.lines) {
			if(!sb.toString().equals("")) {
				sb.append(System.lineSeparator());
			}
			sb.append(il.getAmount()).append(" ").append(il.getGood().getName()).append(": ").append(il.getTotal());			
		}
		sb.append(System.lineSeparator()).append("Sales taxes: ").append(this.getSalesTaxes()).append(System.lineSeparator());
		sb.append("Total: ").append(this.getTotal());
		return sb.toString();
	}

}
