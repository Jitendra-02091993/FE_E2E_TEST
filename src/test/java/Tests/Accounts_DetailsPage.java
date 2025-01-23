package Tests;

import static DataBuilder.DataBuilders.user_Details;
import org.testng.annotations.Test;

import DataBuilder.UserDetails;
import Utils.CommonMethods;
import Utils.GlobalVariables;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class Accounts_DetailsPage {
//	private static final Logger LOGGER = LogManager.getLogger(Home_Page.class);
	private UserDetails userDetails = user_Details();

	@Step("Validating email with invalid data")
	@Test(priority = 1, dependsOnGroups = { "personalDetails_onboardingJourney" }, groups = { "email_ID" })
	@Description("This test will validate email ID with invalid data")
	@Severity(SeverityLevel.NORMAL)
	public void validateEmailFieldWithInvalidData() {
		CommonMethods.sendKeys("emailID", GlobalVariables.inValidEmail);
		CommonMethods.clickOnElementIfVisible("send_Verification_Btn");
		CommonMethods.checkIfElementIsDisplayed("inValid_In_Line_Error_Msg");
	}

	@Step("Validating email with valid data")
	@Test(priority = 2, dependsOnGroups = { "email_ID" }, groups = { "email" })
	@Description("This test will validate email ID with valid data")
	@Severity(SeverityLevel.NORMAL)
	public void validateEmailFieldWithValidData() {
		CommonMethods.sendKeys("emailID", userDetails.getEmailID());
		CommonMethods.clickOnElementIfVisible("send_Verification_Btn");
		CommonMethods.checkIfElementIsDisplayed("otp1");
		CommonMethods.enterOtp();
		CommonMethods.checkIfElementIsDisplayed("verified_Text");
		CommonMethods.clickOnElementIfVisible("continue_Btn");
		CommonMethods.checkIfElementIsDisplayed("send_Verification_Btn");
	}

	@Step("Validating mobile with valid data")
	@Test(priority = 3, dependsOnGroups = { "email" })
	@Description("This test will validate mobile with valid data")
	@Severity(SeverityLevel.NORMAL)
	public void validateMobileFieldWithValidData() {
		CommonMethods.checkIfElementIsDisplayed("country_CodeInputBox");
		CommonMethods.selectCountryCode(84);
		CommonMethods.sendKeys("mobile_InputBox", "8169075328");
		CommonMethods.clickOnElementIfVisible("send_Verification_Btn");
		CommonMethods.checkIfElementIsDisplayed("otp1");
		CommonMethods.enterOtp();
		CommonMethods.checkIfElementIsDisplayed("verified_Text");
		CommonMethods.clickOnElementIfVisible("continue_Btn");
	}

}
