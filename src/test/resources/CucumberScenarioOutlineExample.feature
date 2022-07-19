Feature: This is scenario outline feature
  Scenario Outline: Test multiple page titles
    Given User navigates to '<url>'
    Then user sees page title '<page_title>'
    Examples:
      | url | page_title|
      | http://www.google.lv | Google|
      | https://www.lu.lv | Latvijas Universitāte |
      | https://cv.lv/lv/ | Sākums  CV-Online - darba piedāvājumi, vakances, CV, personāla atlase|
      | https://www.inbox.lv/| Inbox.lv - vienot savējos lielām lietām!|

