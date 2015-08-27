import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.verizon.dispatch.route.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class DispatchDetailsTest {
	User obj = new User();
	private static final Logger logger = LoggerFactory.getLogger(DispatchDetailsTest.class);

	@Test
	public void idTest() {
		logger.info("Inside idTest");

		String tmp = "id";
		obj.setId(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getId());
	}
	@Test
	public void firstNameTest() {
		logger.info("Inside firstNameTest");

		String tmp = "firstName";
		obj.setFirstName(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getFirstName());
	}
	@Test
	public void lastNameTest() {
		logger.info("Inside lastNameTest");

		String tmp = "lastName";
		obj.setLastName(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getLastName());
	}
	@Test
	public void phoneNumberTest() {
		logger.info("Inside phoneNumberTest");

		String tmp = "phoneNumber";
		obj.setPhoneNumber(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getPhoneNumber());
	}
	@Test
	public void emailTest() {
		logger.info("Inside emailTest");

		String tmp = "email";
		obj.setEmail(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getEmail());
	}
	@Test
	public void zipCodeTest() {
		logger.info("Inside zipCodeTest");

		int tmp = 123456;
		obj.setZipCode(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getZipCode());
	}
	@Test
	public void addressDescTest() {
		logger.info("Inside addressDescTest");

		String tmp = "addressDesc";
		obj.setAddressDesc(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getAddressDesc());
	}
	@Test
	public void startDateTest() {
		logger.info("Inside startDateTest");

		Date tmp = new Date();
		obj.setStartDate(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getStartDate());
	}
	@Test
	public void poolModeTest() {
		logger.info("Inside poolModeTest");

		String tmp = "poolMode";
		obj.setPoolMode(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getPoolMode());
	}
	@Test
	public void vehicleTypeTest() {
		logger.info("Inside vehicleTypeTest");

		String tmp = "vehicleType";
		obj.setVehicleType(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getVehicleType());
	}
	@Test
	public void vehicleCapacityTest() {
		logger.info("Inside vehicleCapacityTest");

		int tmp = 123;
		obj.setVehicleCapacity(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getVehicleCapacity());
	}
	@Test
	public void isEnrolledTest() {
		logger.info("Inside isEnrolledTest");

		String tmp = "isEnrolled";
		obj.setIsEnrolled(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getIsEnrolled());
	}
	@Test
	public void pickCountTest() {
		logger.info("Inside pickCountTest");

		int tmp = 123;
		obj.setPickCount(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getPickCount());
	}
	@Test
	public void providerUserIdTest() {
		logger.info("Inside providerUserIdTest");

		String tmp = "providerUserId";
		obj.setProviderUserId(tmp);

		// assert statements
		assertEquals("SUCCESS", tmp, obj.getProviderUserId());
	}
}