/**
 * 
 */
package org.eej.technical.eval;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author Mikel
 *
 */
public class Log4jLoadingTest {
	
	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void test() {
		logger.debug("test");
	}

}
