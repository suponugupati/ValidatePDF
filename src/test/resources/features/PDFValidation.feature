@PDFValidation
Feature: Validate content of a PDF file


   @T4
  Scenario Outline: Validate the contents of pdf file
    Given I have a "<Parameter>" PDF URL
    When I validate the PDF content of "<Parameter>"
    Examples:
    | Parameter |
    | SOC_369_Agency_Relative_Guardianship_Disclosure |
     |STRTP Admission Agreement By Agency (SOC 154C) |




