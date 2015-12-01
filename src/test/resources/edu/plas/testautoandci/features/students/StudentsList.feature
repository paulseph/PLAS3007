@students @lecture9
Feature: Students List

  @count
  Scenario: 10 students in list
    Given I navigate to http://40.127.132.250:8090/listStudents
    Then there are '10' students in the list

  @studentFields
  Scenario: All students have all fields
    Given I navigate to http://40.127.132.250:8090/listStudents
    Then all students have an ID, first name, last name, country and mobile

  @studentName
  Scenario: Student name is present in list
    Given I navigate to http://40.127.132.250:8090/listStudents
    Then '3' students in the list are called 'Francesco'

  @studentNationalityPresent
  Scenario: Student nationality is present in list
    Given I navigate to http://40.127.132.250:8090/listStudents
    Then 'at least 2' students are from 'Malta'

  @studentNationalityNotPresent
  Scenario: Student nationality is present in list
    Given I navigate to http://40.127.132.250:8090/listStudents
    Then '0' students are from 'Belarus'

  @studentMobile
  Scenario: Student mobile numbers are numeric
    Given I navigate to http://40.127.132.250:8090/listStudents
    Then all mobile numbers are solely numeric