Feature: Search ID From Grid and Print Values In A Row

Scenario: Validate if the user can input an ID and return the values in the row

Given the web browser is open
And the user is on the Grid Demo page
And the user clicks on the cookie banner
When the user enters a valid ID
Then the details of the ID will be printed out