package StepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import PageObjects.GridDemoPage;

public class GridDemoSteps {

	WebDriver driver;
	GridDemoPage gridDemo;
	int id;

	@Given("the web browser is open")
	public void openWebBrowser() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/main/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@And("the user is on the Grid Demo page")
	public void goToGridDemoPage() {
		driver.get("https://demo.aspnetawesome.com/GridDemo");
	}

	@And("the user clicks on the cookie banner")
	public void clickCookieBanner() {
		gridDemo = new GridDemoPage(driver);
		gridDemo.getCookieOkBtn().click();
	}

	@When("the user enters a valid ID")
	public void inputGridDemoId() {

		boolean idIsInteger = false;
		while (!idIsInteger) {
			try {
				String inputString = JOptionPane.showInputDialog("Please enter the id:");
				id = Integer.parseInt(inputString);
				idIsInteger = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "The input is not a valid id.");
			}
		}
	}

	@Then("the details of the ID will be printed out")
	public void findIdAndPrintRowValues() throws InterruptedException {

		boolean idIsFound = false;
		int gridPage = 1;

		while (!idIsFound) {
			WebElement table = gridDemo.getTable();
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				for (WebElement cell : cells) {
					String cellText = cell.getText();
					if (cellText.equals(Integer.toString(id))) {
						idIsFound = true;

						String personText = getSiblingCellText(cell, 1);
						String foodText = getSiblingCellText(cell, 2);
						String countryText = getSiblingCellText(cell, 3);
						String dateText = getSiblingCellText(cell, 4);
						String locationText = getSiblingCellText(cell, 5);
						String chefText = getSiblingCellText(cell, 6);

						JOptionPane.showMessageDialog(null,
								"Input Id number: " + id + 
								"\nPerson: " + personText + 
								"\nFood: " + foodText + 
								"\nCountry: " + countryText + 
								"\nDate: " + dateText + 
								"\nLocation: " + locationText + 
								"\nChef: " + chefText);

						System.out.println("Input Id number: " + id);
						System.out.println("Person: " + personText);
						System.out.println("Food: " + foodText);
						System.out.println("Country: " + countryText);
						System.out.println("Date: " + dateText);
						System.out.println("Location: " + locationText);
						System.out.println("Chef: " + chefText);

						break;
					}
				}
			}

			if (!idIsFound) {
				int firstRowId = getFirstRowId();
				if (id < firstRowId) {
					try {
						gridPage++;
						gridDemo.getGridPageBtn(gridPage).click();
						Thread.sleep(2000);
					}

					catch (NoSuchElementException e) {
						promptIdCanNotBeFound();
						break;
					}
				} else {
					promptIdCanNotBeFound();
					break;
				}
			}
		}

	}

	public String getSiblingCellText(WebElement idCell, int position) {
		return gridDemo.getSiblingCell(idCell, position).getText();
	}

	public int getFirstRowId() {
		String firstRowString = gridDemo.getTableFirstRowId().getText();
		return Integer.parseInt(firstRowString);
	}

	public void promptIdCanNotBeFound() {
		JOptionPane.showMessageDialog(null, "The ID can not be found.");
		System.out.println("The ID can not be found.");
	}

}
