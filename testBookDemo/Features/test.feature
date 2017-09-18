Feature: Verify that answers selected in test demo in testbook site

  Scenario: Login into testbook site
    Given username "testcucu@gmail.com" and password "testbook$1"
    When I login using above credentials
    Then I am redirected to my home page

  Scenario: Go to Test and Enroll into any one free test available
    Given that you are present on test page
    When I select the courses and click enroll
    Then I must see the free courses available for test
    And I am able to add that test using add my test button

  Scenario: Attempt the test and verify the answers selected after submission
    Given that I select any of the added test to attempt
    When I attempt the test by answering the questions
    And I Submit the test
    Then after submission I must see the answers that I selected along with solutions
