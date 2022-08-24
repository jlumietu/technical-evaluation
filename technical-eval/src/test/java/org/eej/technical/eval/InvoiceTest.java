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
 * <p>
 * This is the test class which performs the tasks to achieve the completion of the proposed exercise. 
 * It has been built as a JUnit test.
 * </p>
 * <p>
 * The output file is generated with the name {@value #OUTPUT_RECEIPT_TXT} whilst it can be seen
 * in the generated log output at the same time.
 * </p>
 * <p>
 * The input is provided as the constant variable {@link #INVOICES}.
 * </p>
 * <p>
 * The output of the last invoice (#3) differs from what was expected, but after several checks, no 
 * error has been found on the strategy used to solve the whole exercise.
 * </p> 
 * 
 * 
 * @author Mikel Ibiricu Alfaro
 *
 */
public class InvoiceTest {
	
	private static final String OUTPUT_RECEIPT_TXT = "output-receipt.txt";

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
		Path path = Paths.get(dir, "target",OUTPUT_RECEIPT_TXT);
		if(path.toFile().exists()) {
			path.toFile().delete();
		}
		
		for(Invoice invoice : INVOICES) {
			String info = invoice.getInfo();
			logger.debug(System.lineSeparator() +  info);
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
