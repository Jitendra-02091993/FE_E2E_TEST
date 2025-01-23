package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import BaseSetUp.BaseSetUps;
import Utils.CommonMethods;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class Home_Page extends BaseSetUps {
	private static final Logger LOGGER = LogManager.getLogger(Home_Page.class);

	@Step("Validating Home Page after browser is launched")
	@Test(priority = 1, groups = { "base" })
	@Description("This test will validate homePage functionality")
	@Severity(SeverityLevel.CRITICAL)
	public void validate_HomePage() {
		CommonMethods.launch_Browser();
		LOGGER.info("Landed on HomePage Successfully");
		CommonMethods.clickOnElementIfVisible("accept_Cookies");
		CommonMethods.checkIfElementIsDisplayed("accept_Cookies");
	}

	@Step("Initiating Onboarding journey")
	@Test(priority = 2, dependsOnGroups = { "base" }, groups = { "initiate_Onboarding" })
	@Description("This method will initiate onboarding journey")
	@Severity(SeverityLevel.CRITICAL)
	public void initiate_Onboarding() {
		CommonMethods.clickOnElementIfVisible("applyNow_Btn");
		CommonMethods.wait(2);
		CommonMethods.clickOnElementIfVisible("applyNow_Btn");
		CommonMethods.clickOnAllFourDoc();
		CommonMethods.clickOnElementIfVisible("startApplicationButton");
		CommonMethods.checkIfElementIsDisplayed("sole_Account_Btn");
	}

	

}
