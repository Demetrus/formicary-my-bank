package helpers;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.abc.helpers.TextHelper;

public class TextHelperTest {

	@Test
	public void test_formatToSingularOrPlural() {
		assertEquals("0 accounts", TextHelper.formatToSingularOrPlural(0, "account"));
		assertEquals("1 account", TextHelper.formatToSingularOrPlural(1, "account"));
		assertEquals("2 accounts", TextHelper.formatToSingularOrPlural(2, "account"));
		assertEquals("11 accounts", TextHelper.formatToSingularOrPlural(11, "account"));
		assertEquals("21 account", TextHelper.formatToSingularOrPlural(21, "account"));
		assertEquals("35 accounts", TextHelper.formatToSingularOrPlural(35, "account"));
	}

	@Test
	public void test_toAbsoluteDollars(){
		assertEquals("$100.00", TextHelper.toAbsoluteDollars(new BigDecimal("100.00009")));
		assertEquals("$0.00", TextHelper.toAbsoluteDollars(new BigDecimal("0.00009")));
		assertEquals("$100.00", TextHelper.toAbsoluteDollars(new BigDecimal("100")));
		assertEquals("$100.07", TextHelper.toAbsoluteDollars(new BigDecimal("-100.069")));
	}
}
