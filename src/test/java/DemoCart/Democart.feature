Feature: To test democart

@smokeTest
Scenario: To test navigation to iPhone
	Given user on democart page
	When  user click on phone then iphone
	And navigate back to home page
	Then navigation successful

@smokeTest
Scenario: To test navigation to nikon Camera
	Given user on democart page
	When  user click on camera then nikon
	And navigate back to home page
	Then navigation successful

@smokeTest @regressionTest
Scenario Outline: Searching products
	Given user on democart page
  When I entered product <pname>
  And select the product
  And add it to cart
  And navigate back to home page
  Then added succesfully
    Examples:
      | pname |
      | "notebook" |
      | "tablet" |
      | "iMac"| 