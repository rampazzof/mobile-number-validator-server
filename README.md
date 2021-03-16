# `MOBILE NUMBER VALIDATOR (South Africa)`
Mobile Number Validator is a backend service which is able to recognize if the submitted mobile number is in the right format for South Africa.
Valid South Africa mobile number starts with 27 and is 11 chars long.

## `Openapi documentation`
After running service (local) visit http://localhost:8080/swagger-ui.html

N.B. Csv file header must be `"id", "mobile_number"`

## `Run local`
1. Checkout project
2. Run `mvn clean package`
3. Run `java -jar ./target/mobile-number-validator-0.0.1-SNAPSHOT.jar`

If you want perform strong validation* set ENV `STRONG_VALIDATION` to `true`

*the difference between strong and light validation is that strong validation only cleans up the string by removing chars (letter, symbol) while light validation also looks for the substring starting with 27 and length 11.