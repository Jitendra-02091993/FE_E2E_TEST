package PageLocators;

import org.openqa.selenium.By;


public class AccountsPage_Locators {
	public static int i = 0;
	
	public static By emailID = By.xpath("//input[@id=\"email\"]");
	public static By inValid_In_Line_Error_Msg = By.xpath("//p[@id=\"email-helper-text\"]");
	public static By send_Verification_Btn = By.xpath("//button[text()=\"Send verification code\"]");
	public static By otp1 = By.xpath("//input[@id=\"otp_1\"]");
	public static By otp2 = By.xpath("//input[@id=\"otp_2\"]");
	public static By otp3 = By.xpath("//input[@id=\"otp_3\"]");
	public static By otp4 = By.xpath("//input[@id=\"otp_4\"]");
	public static By otp5 = By.xpath("//input[@id=\"otp_5\"]");
	public static By otp6 = By.xpath("//input[@id=\"otp_6\"]");
	public static By verified_Text = By.xpath("//p[text()=\"Verified\"]");
	
	// Mobile Locator
	public static By country_CodeInputBox = By.xpath("//input[@id=\"code-autocomplete\"]");
	public static By country_Code_DropDwn = By.xpath("//button[@title=\"Open\"]//*[name()='svg']");
	public static By listOf_Al_Country_Code = By.xpath("//ul[@id=\"code-autocomplete-listbox\"]/li");
	public static By country_Code = By.xpath("//li[@id=\"code-autocomplete-option-"+i+"\"]");
	public static By mobile_InputBox = By.xpath("//input[@id=\"mobile\"]");
	
	
	

}
