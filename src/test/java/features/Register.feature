Feature: First test in the website  

  Scenario Outline: Register the the website successfully
    Given Open url
     When I enter <name>, <email> and <password>
      And I check the check-box "Check me out if you Love IceCreams!"
      And I select the item "Female" in the listbox
      And I choose the radio button "Employed"
      # And I input my birthday in the date of birth field
      And I click submit button
     Then I receive a successful notification "Success! The Form has been submitted successfully!."

    Examples: 
      | name       | email  | password | gender | 
      | Test Name1 | test99 | 123      | Male   | 
      | Test Name2 | test99 | 123      | Male   | 