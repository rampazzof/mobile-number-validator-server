# `MOBILE NUMBER VALIDATOR (South Africa)`
Mobile Number Validator is a backend service which is able to recognize if the submitted mobile number is in the right format for South Africa.

## `Openapi documentation`
After running service (local) visit http://localhost:8080/swagger-ui.html

N.B. Csv file header must be `"id", "mobile_number"`

## `Run local`
1. Checkout project
2. Run `mvn clean package`
3. Run `java -jar ./target/mobile-number-validator-0.0.1-SNAPSHOT.jar`