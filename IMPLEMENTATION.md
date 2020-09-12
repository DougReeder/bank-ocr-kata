Bank OCR Kata Implementation Notes
==================================

This implements User Story 1.

User Story 1 doesn't specify how bad input is handled, 
so the code attempts to extract info from a slightly mangled file
(see fourAccountNumbers.txt),
but doesn't attempt to recover or report errors (aside from the console).

Due to running out of time, the following aspects of User Story 1 have
not been implemented:
* account numbers are not checked for length

The following heuristics were implemented, despite not being mentioned in the requirements
* entries consist of three lines of OCR data, preceded by zero or more lines of non-OCR text.

