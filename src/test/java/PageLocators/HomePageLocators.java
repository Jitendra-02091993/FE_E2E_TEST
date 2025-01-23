package PageLocators;

import org.openqa.selenium.By;

public class HomePageLocators {
	
	public static By accept_Cookies = By.xpath("//button[@id=\"onetrust-accept-btn-handler\"]");
	public static By applyNow_Btn = By.xpath("(//button[@id=\"topBannerSection\"])[1]");
	public static By fscs_Doc = By.xpath("//button[@data-testid=\"FSCS Information Sheet\"]");
	public static By savings_Doc = By.xpath("//button[@data-testid=\"Savings Account Conditions\"]");
	public static By kfsc_Doc = By.xpath("//button[@data-testid=\"Key Features & Summary Box Document\"]");
	public static By privacy_Doc = By.xpath("//button[@data-testid=\"Privacy Policy\"]");
	public static By startApplicationButton = By.xpath("//div/button[text()=\"Start Application\"]");
	public static By sole_Account_Btn = By.xpath("//div[@id=\"selectAccountType\"]/label[1]");
	public static By joint_Account_Btn = By.xpath("//div[@id=\"selectAccountType\"]/label[2]");
	public static By continue_Btn = By.xpath("//button[text()=\"Continue\"]");


}
