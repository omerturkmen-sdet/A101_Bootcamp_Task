# A101 Bootcamp Task

This repository was created to test the A101 shopping website. The tests were written in Java, Selenium WebDriver, Cucumber, JUnit languages using maven project management tool. End-to-end test scenarios were created where you search and select a specific product on the store website, then enter the member and address information and make a payment.

## Additional Information 
### Page Object Model

The Page Object Model is used to store web elements. This design principle allows possible changes to be localized within a single class.  In addition, the creation of methods related to these elements provides more readable and reusable code in the framework.

### Singleton Design Pattern

Selenium WebDriver used to interact with web elements from different pages. Singleton design pattern used to avoid creating new object for every pages. Driver.java class allows to use same Selenim WebDriver object for each scenario and it doesn't required passing WebDriver object to methods and classes.
