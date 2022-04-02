Feature: Demo of Maven and Junit integration - Login

@login_valid @login
Scenario Outline: Login happy path case repeat N times
  When User attempts to login with Username "<username>" and Password "<password>"
  Then Login should be successful

  Examples:
    |username                        | password           |
    |creativity2020@mailinator.com | creativity2020@@  |


@login_invalid @login
Scenario Outline: Login happy path case repeat N times
  When User attempts to login with Username "<username>" and Password "<password>"
  Then Login should NOT be successful

  Examples:
    |username                        | password           |
    |creativity2020@mailinator.com | creativity2020@@  |
