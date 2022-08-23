/**
 * 
 */
package org.eej.technical.eval;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eej.technical.eval.model.SalesTax;
import org.eej.technical.eval.model.Good;
import org.eej.technical.eval.model.Invoice;
import org.eej.technical.eval.model.InvoiceLine;
import org.junit.Test;

/**
 * @author DOIBALMI
 *
 */
public class InvoiceTest {
	
	private static Logger logger = LogManager.getLogger();

	private static final List<Invoice> INVOICES = 
		Arrays.asList(
			new Invoice(
				Arrays.asList(
					new InvoiceLine(
						new Good(
							"book",
							12.49,
							SalesTax.EXEMPT
						),
						1							
					),
					new InvoiceLine(
						new Good(
							"music CD",
							14.99,
							SalesTax.BASIC
						),
						1
					),
					new InvoiceLine(
						new Good(
							"chocolate bar",
							0.85,
							SalesTax.EXEMPT
						),
						1
					)
				)
			),
			new Invoice(
					Arrays.asList(
							new InvoiceLine(
								new Good(
									"imported box of chocolates",
									10.00,
									SalesTax.IMPORTED
								),
								1							
							),
							new InvoiceLine(
								new Good(
									"imported bottle of perfume",
									47.50,
									SalesTax.IMPORTED,SalesTax.BASIC
								),
								1
							)
						)
			),
			new Invoice(
					Arrays.asList(
							new InvoiceLine(
								new Good(
									"imported bottle of perfume",
									27.99,
									SalesTax.IMPORTED,SalesTax.BASIC
								),
								1							
							),
							new InvoiceLine(
								new Good(
									"bottle of perfume",
									18.99,
									SalesTax.BASIC
								),
								1
							),
							new InvoiceLine(
								new Good(
									"packet of headache pills",
									9.75,
									SalesTax.EXEMPT
								),
								1
							),
							new InvoiceLine(
								new Good(
									"box of imported chocolates",
									11.25,
									SalesTax.IMPORTED
								),
								1
							)
						)
			)
		);
	
	@Test
	public void test() {
		String dir = System.getProperty("user.dir");
		Path path = Paths.get(dir, "target","output-receipt.txt");
		if(path.toFile().exists()) {
			path.toFile().delete();
		}
		
		for(Invoice invoice : INVOICES) {
			String info = invoice.getInfo();
			logger.debug(info);
			try {
				Files.write(
						path, 
						new StringBuilder(info).append(System.lineSeparator()).append(System.lineSeparator()).toString().getBytes(StandardCharsets.UTF_8), 
						StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND
				);
			} catch (IOException e) {
				logger.warn("Error writing into output file " + e.getMessage(), e);
			}
		}
		
	}
	
}
