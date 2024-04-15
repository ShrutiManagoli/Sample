Feature: To verify the functionality of the ToDo application.

  As a user of the ToDo website
  I should be able to create my to do list
  I should be able to manage my items

  Background:
    Given I am on the ToDo page

    Scenario Outline: User should be able to add list of items in the todo app
      Given launch the todo application
      Then user should be able to add the list of items "<items>"

      Examples:
        | items   |
        | grocery |
    #    | kids school pickup |
    #    | doctor appointment |

    Scenario Outline: Item is marked as done when user clicks on the radio button
      Given launch the todo application
      Then user should be able to add the list of items "<items>"
      When clicked on radio button item is marked as done
      Examples:
        | items   |
        | grocery |
      #  | kids school pickup |
      #  | doctor appointment |


  Scenario Outline: Active items are shown when user clicks on active link
    Given launch the todo application
    Then user should be able to add the list of items "<items>"
    When clicked on the active link
    Then only the active items are displayed

    Examples:
      | items   |
      | grocery |
    #  | Ticket             |
    #  | kids school pickup |
    #  | doctor appointment |

  Scenario Outline: User click on close icon the item is deleted
    Given launch the todo application
    Then user should be able to add the list of items "<items>"
    When clicked on the close icon
    Then the item "<items>" is deleted

    Examples:
      | items   |
      | grocery |
