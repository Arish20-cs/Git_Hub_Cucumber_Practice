Feature:
	Git crud Operations
Background:
	Given set the base url

Scenario Outline:
	When I send post request to "/user/repos"
	|title|<title>|
	|desc|<desc>|
	Then response should contain status as 201
	And check visibility as "false"
	
	When I send get request with "<title>" to "/repos"
	Then response should contain status as 200
	
	When I send patch request with "<title>" to "/repos"
	Then response should contain status as 200
	And check visibility as "true"
	
	When I send delete request with "<title>" to "/repos"
	Then response should contain status as 204
Examples:
|title|desc|
|demo1234|repocreated|
|demo4567|repocreated|