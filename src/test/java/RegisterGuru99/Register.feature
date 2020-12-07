@smokeTest
Feature: To test Registeration in guru99
I want user should register with valid email and got login credentials.
In case of invalid email it should throw prompt.

@CheckVisitHere @smt
Scenario: To check visit here link
	Given Navigate to Guru99Bank Login page
	When user clicks on visit here link
	Then it should navigate to registeration page

@PositiveTest @SmokeTesting
Scenario: Successful Registeration
	Given User on Registeration page
	When user enters email id "khan@sa.com"
	And email id is valid and clicks submit
	Then registeration is successful and gets valid login credentails

@NegativeTest @SmokeTesting
Scenario: Unsuccessful Registeration
	Given User on Registeration page
	When user enters email id "khan@com"
	But email id is invalid
	Then invalid email popup comes
 
@ParamTest @RegressionTesting @SmokeTesting
Scenario Outline: Validate email id
	Given User on Registeration page
	When user enters email id <email>
	Then validated email id <result>
		Examples:
			| email | result |
			| "kha@sak.com" | "valid" |
			| "kh1sak.com" | "invalid" |
			| "kha1@sak1.com" | "valid" |
			| "khsak.com" | "invalid" |
