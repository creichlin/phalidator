GOAL
====

The goal of this lib is to create userinput validator code for different languages.

The first implementation would be for javascript and java.

IDEAS
=====

The validators are written in a simple script which defines validation expressions for different fields of a model.

The script is parsed and a tree is built. Antlr4 is used and this step is implemented in java.

The tree can be used to validate entities (beans, maps, ...) directly. This would be more for testing though.

Different visitors will produce code for different target languages which can be used to validate userinput.

The target can be for example, javascript/html-forms or javascript/json or java/beans.

PROGRESS
========

Antlr4 parser and visitor to create validation tree.

Example
=======

Example for a person:

    person {
      firstName: length >= 2
      lastName: length >= 2
      customerNumber: length == 6 and letters in "0123456789"
      zipCode: (length in 4..5 and letters in "0123456789")
      street: length in 1..64
      streetNumber: length in 1..6
      city: length >= 2
      language: . in ["de", "fr", "it", "en", "es"]
      title: . in ["MR", "MRS"]
    }

Should generate code that could look like following in js:
    
    personValidator = formPhalidator.person(document.getElementById("FORM_ID"));
    if(personValidator.valid()) {
      // kiss your secretary
    } else {
      for(var key in personValidator.getInvalidFields()) {
        // iterates over keys like "person.street"
        // get that fields alert div, display it and set the proper errormessage
      }
    }

Similar code in java which can be given a parameter map or some beans
