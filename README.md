You can find the project at following GitHub link.

**Link -->**  https://github.com/sidd-studying/QualiTest

Instructions/Details about the project:

This project is developed based on Page Object Model pattern and Cucumber BDD. It also conisits of all the requirements mentoned in the question set. Wishlist.feature consists of the BDD scenario mentioned in the test. TestRunner.java is the class that can be used for executing the tests. You may also execute the test case from Wishlist.feature file directly.

**Feature file :** You may add any products(products that are available on the gievn website) inside the scenario. But make sure you change the number of products expected, which are mentioned on Wishlist.feature file.

**Class/Java files -->**
HeperUtils.java class contains all the helper methods. 
We have used Cucumber Picocontainer to share daya between the steps. 
QualiTestDAO contains all the data that is shared between steps. 
SeleniumInitialize initalises the WebDriver and kills the webdriver instance once scenario is completed. 
ActionFailedException is the user defined exception class.
Each UI page is followed by a step definition class and a controller class. All the UI actions are performed inside controller clasees.


**Issues in the existing application-->**
When we try to add a product with name "Women's dress", the ADDTOACART button navigates the call to an alltogether different WebPage. And hence the original call fails. This is an issue
When we try to add a product to cart which has price within a range (say 20£ = 25£) it is difficult as it says to add the opition/options, which actually never worked even when I tired it(I need more clarification on it).
Just clone the procject and run TestRunner.java file. Mention the products inside first step of the BDD feature file, that you want to be added in the wish list.


**Improvements that can be made-->**
We can use screen shot feature upon scenario failure.
We can also use Ashot plugin to take screen shot of the full page (includiing scroll option).
We may use APACHE POI and store the test cases/data inside it. This will help us maintain the test data more efficiently.
Can use Log4J for logging.
We can also create a hybrid framework out of the same one which would do FrontEnd/GUI testing(using Selenium) and backend/API/MicroService testing (using Rest-Assured)
Can incorprate JDBC connection if required.
