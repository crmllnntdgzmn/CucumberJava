package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GridDemoPage {

	WebDriver driver;

	public GridDemoPage(WebDriver driver) {
		this.driver = driver;
	}

	By cookieOkBtn = By.id("btnCookie");
	By table = By.id("Grid1");
	By tableFirstRowId = By.cssSelector("#Grid1 .awe-row:nth-of-type(1) td:nth-of-type(1)");

	By gridPageBtn(int gridPage) {
		return By.xpath("//button[text()='" + gridPage + "']");
	}

	By siblingCell(WebElement idCell, int position) {
		return By.xpath("following-sibling::td[" + position + "]");
	}

	public WebElement getCookieOkBtn() {
		return driver.findElement(cookieOkBtn);
	}

	public WebElement getTable() {
		return driver.findElement(table);
	}

	public WebElement getTableFirstRowId() {
		return driver.findElement(tableFirstRowId);
	}

	public WebElement getGridPageBtn(int gridPage) {
		return driver.findElement(gridPageBtn(gridPage));
	}

	public WebElement getSiblingCell(WebElement idCell, int position) {
		return idCell.findElement(siblingCell(idCell, position));
	}

}
