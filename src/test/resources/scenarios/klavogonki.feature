Feature: Bot for site klavogonki.ru

  Background:I am on header screen
    Given Open site "https://klavogonki.ru/go?type=normal"

  Scenario: Bot run the game and put the words
    When Start the game
    And Wait for a start the game
    And Enter the highlighted world
    Then Game is ended and symbol in minute more 1500

