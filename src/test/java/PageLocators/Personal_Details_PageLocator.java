package PageLocators;

import org.openqa.selenium.By;

public class Personal_Details_PageLocator {
	
	public static By first_Name = By.xpath("//input[@id=\"firstName\"]");
	public static By middle_Name = By.xpath("//input[@id=\"middleName\"]");
	public static By last_Name = By.xpath("//input[@id=\"lastName\"]");
	public static By day = By.xpath("//input[@id=\"day\"]");
	public static By month = By.xpath("//input[@id=\"month\"]");
	public static By year = By.xpath("//input[@id=\"year\"]");
	public static By address_SearchBox = By.xpath("//input[@id=\"currentSearch\"]");
	public static By address1 = By.xpath("//ul[@data-testid=\"react-loqate-list\"]/li/span[1]");
	public static By address2 = By.xpath("//ul[@data-testid=\"react-loqate-list\"]/li/span[2]");
	public static By removeAddress= By.xpath("//p[text()=\"Remove address\"]");
	public static By six_month_More = By.xpath("//div[@id=\"duration\"]/label[position()=1]");

}
