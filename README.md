# REST API automation using Rest Assured

This project contains a Maven + JUnit 5 test suite that demonstrates REST API automation with **Rest Assured**.

## Prerequisites
- Git
- Java 17 (or later)
- Maven 3.9+
- IntelliJ IDEA (Community or Ultimate)

## How to checkout the project
If you are cloning from a remote repository:

```bash
git clone <your-repo-url>
cd gitproj
```

If you already have the repository and just want the latest code:

```bash
git checkout <branch-name>
git pull
```

## Open in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Click **Open** and select the `gitproj` folder.
3. IntelliJ will detect `pom.xml` as a Maven project.
4. If prompted, click **Load Maven Project**.
5. Wait for Maven indexing and dependency download.

If tests are not visible:
- Ensure the project was opened from the **root folder** that contains `pom.xml`.
- In the Maven tool window, click **Reload All Maven Projects**.
- Verify Java SDK 17 is configured in **Project Structure**.

## Run the tests
From terminal (project root):

```bash
mvn clean test
```

From IntelliJ:
1. Go to `src/test/java/api/RestAssuredApiTest.java`.
2. Click the green run icon next to the class or test method.
3. Run all tests.


## Run tests and generate Allure report
Use these commands from the project root:

```bash
mvn clean test
mvn allure:report
```

Generated artifacts:
- Raw results: `allure-results`
- HTML report: `target/site/allure-maven-plugin/index.html`

To open the report quickly:

```bash
xdg-open target/site/allure-maven-plugin/index.html
```

## Expected output
- Maven builds the project.
- JUnit runs `RestAssuredApiTest`.
- All tests should pass.

## What is included
- Maven build configuration in `pom.xml`
- API tests in `src/test/java/api/RestAssuredApiTest.java`
- Local API mocking using `MockWebServer` (no external API dependency)

## Test coverage
- `GET /posts/1` status and field assertions
- `GET /posts` response list size assertion
- `POST /posts` status and response payload assertions
