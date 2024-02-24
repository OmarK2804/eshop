## Tutorial 1

### Reflection 1

Java springboot(MVC) template urges me to be more adhere to coding standards. Springboot makes each part of the program completely apart, that makes the component of the program to be built and modified independently. This relates to coding principles to ensure maintainability and readability of the codebase.

In the code, I implemented a standard naming conventions to make the code more readable. I also implement a secure coding practice by using UUID for the product id. At last, I also make a git branch for each feature as an implementation of separation of concern.

### Reflection 2

1. Well, for the time being, I feel the unit test is already sufficient. I think the amount of unit tests in a class can be a lot. That is because we have to make a test for positive and negative scenario for each feature. Although code coverage does give a percentage of measure of whether the code contains bugs, 100% code coverage still doesn't ensure that there is no bug in the code.

2. Well although I think the code will still be clean and doesn't reduce its quality, it is redundant to create the new test because the test can be incorporated in the previous test.

## Tutorial 2

### Reflection 

1. Lots of my codes in the test directory define the same product variables over and over again (redundant). So to fix it, I define the variables in the setUp method that will run before each test. Other than that, I also fix my update method in ProductRepository to return null as an error handling.

2. In my opinion, my code has implemented the definition of CI/CD. CI is implemented by using sonarcloud and scorecard as code review tools and jacoco for code coverage. CD is implemented by using Koyeb as an auto-deploy mechanism.

## Tutorial 3

### Reflection

1. For the SRP, I have moved the car controller class from product repository so product repository has single responsibility which is product. For the OCP, I moved the set UUID for product from the product model module to the product repository. For the ISP, the interfaces have already been segregated. For the DIP, I create interfaces for both Product and Car repository so Product and Car repository functions depend on interfaces.

2. After applying SOLID, my code should be understandable, flexible, and maintainable. By applying SRP, the code is easier to maintain. By applying OCP, the code is more flexible as we can add new code without changing the source code. By applying ISP and DIP, the code becomes easier to test as it depends on abstractions.

3. Not applying LSP can result in inconsistent behavior between classes.