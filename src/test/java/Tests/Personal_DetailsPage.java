package Tests;

import static DataBuilder.DataBuilders.user_Details;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import DataBuilder.UserDetails;
import Utils.CommonMethods;
import Utils.DateUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class Personal_DetailsPage {
	private static final Logger LOGGER = LogManager.getLogger(Personal_DetailsPage.class);
	private UserDetails userDetails = user_Details();
	
	@Step("Validating personalDetails_onboardingJourney")
	@Test(priority = 2, dependsOnGroups = { "initiate_Onboarding"},groups= {"personalDetails_onboardingJourney"})
	@Description("This test will cover the personal details")
	@Severity(SeverityLevel.CRITICAL)
	public void personalDetails_onboardingJourney() throws IOException {
		CommonMethods.clickOnElementIfVisible("sole_Account_Btn");
		CommonMethods.clickOnElementIfVisible("continue_Btn");
		CommonMethods.checkIfElementIsDisplayed("first_Name");
		CommonMethods.sendKeys("first_Name", userDetails.getFirstName());
		CommonMethods.sendKeys("middle_Name", userDetails.getMiddleName());
		CommonMethods.sendKeys("last_Name", userDetails.getLastName());
		CommonMethods.sendKeys("day", DateUtils.dateCalculate().get("date"));
		CommonMethods.sendKeys("month", DateUtils.dateCalculate().get("month"));
		CommonMethods.sendKeys("year", DateUtils.dateCalculate().get("year"));
		CommonMethods.clickOnElementIfVisible("continue_Btn");
		CommonMethods.selectAddressViaLocate("address_SearchBox", "AL38LE", "address1", "80B");
		CommonMethods.checkIfElementIsDisplayed("removeAddress");
		CommonMethods.clickOnElementIfVisible("six_month_More");
		CommonMethods.clickOnElementIfVisible("continue_Btn");
	}
}
