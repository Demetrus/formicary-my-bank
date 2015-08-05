package helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.junit.Test;
import com.abc.helpers.DateProvider;

public class DataProviderTest {

	@Test
	public void test_getInstance() {

		DateProvider dateProvider1 = DateProvider.getInstance();
		DateProvider dateProvider2 = DateProvider.getInstance();

		assertEquals(dateProvider1, dateProvider2);
	}

	@Test
	public void test_now() {
		Date before = new Date();
		this.halt();
		Date now = DateProvider.getInstance().now();
		this.halt();
		Date after = new Date();
		assertTrue(now.compareTo(before) > 0 && now.compareTo(after) < 0);
	}

	@Test
	public void test_justDateTomorrow() {
		throw new UnsupportedOperationException("Test not implemented!");
	}

	@Test
	public void test_addDaysToDate() {
		throw new UnsupportedOperationException("Test not implemented!");
	}

	@Test
	public void test_getDaysInThisYear() {
		throw new UnsupportedOperationException("Test not implemented!");
	}

	private void halt() {
		try {
			Thread.sleep(1 * 1000);
		}
		catch (Exception e) {

		}
	}
}
