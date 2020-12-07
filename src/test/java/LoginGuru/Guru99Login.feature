@smokeTest
Feature: To test Login in guru99
I want user should login with valid username and password.
In case of invalid username or password it should throw an error.

Scenario: Successful Login
Given Navigate to Guru99Bank Login page
When  username and password are entered
|mngr296614|Ajuqunu|
And both are correct
Then Login is successful