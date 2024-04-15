Feature: Login functionality for Clerk user

  As a user of the application
  I should be able to login as clerk
  Clerk should be able to upload a csv file

  Background:
    Given I am on the Application login page

  Scenario: Successful upload of csv file with clerk as use /j/ r
    Given I have entered valid username "clerk" and password "clerk"
    Then User should be logged in successfully
    When I upload a valid CSV file and verify the success message
    Then navigate to the homepage

#  Scenario: Invalid upload of csv file with clerk as user shows error message
#    Given I have entered valid username "clerk" and password "clerk"
#    Then User should be logged in successfully
#    When I upload a invalid CSV file
#
#  Scenario:Book Keeper should be able to generate a Tax Relief Egress file
#    Given I have entered valid username "bk" and password "bk"
#    Then User should be logged in successfully
#    When User generates Tax Relief file
#
#  Scenario Outline: Field validation
#    Given I have the field
#    Then verify that the field validation for "<name>" having "<value>" is correct
#
#    Examples:
#      | name          | value               |
#      | natid         | natid-1234          |
#      | name          | Alex                |
#      | gender        | MALE                |
#      | birthDate     | 1942-01-01T23:16:59 |
#      | deathDate     | 2021-09-03T07:19:23 |
#      | browniePoints | 100                 |
#
#  Scenario Outline: Salary & Tax Paid Field validation
#    Given I have the field
#    Then verify that the salary and Tax Paid field validation for "<name>" having "<value>" is correct
#
#    Examples:
#      | name   | value    |
#      | salary | 20030.45 |
#      | tax    | 2000.23  |
#      | salary | 1000.00  |










