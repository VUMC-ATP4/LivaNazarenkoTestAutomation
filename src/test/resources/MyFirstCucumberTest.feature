Feature: My first cucumber feature

  Scenario: User can see correct title Google
   Given User navigates to 'http://www.google.lv'
    Then user sees page title 'Google'

  Scenario:  User can see correct title LU
    Given User navigates to 'https://www.lu.lv'
    Then user sees page title 'Latvijas Universitāte'




