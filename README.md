Goal
====

The goal of this lib is to create userinput validator code for different languages.

The first implementation would be for javascript and java.

Idea
====

The validators are written in a simple script which defines validation expressions for different fields of a model.

The script is parsed and a tree is built. Antlr4 is used and this step is implemented in java.

The tree can be used to validate entities (beans, maps, ...) directly. This would be more for testing though.

Different visitors will produce code for different target languages which can be used to validate userinput.

The target can be for example, javascript/html-forms or javascript/json or java/beans.

Progress
========

  - Antlr4 parser and visitor to create validation tree.
  - Java Map validator, working directly on the validation tree. Mainly for tests. Can be used but needs all dependencies.
  - Javascript obj/hash/dict validator.

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
    
Build
=====

The default ant target will create a jar dist/phalidator-cli.jar including all dependencies which can be run an generates the validator code. Ivy needs to be included
if not already in your antlib folder.

    ant

or

     -lib /usr/share/java/ivy.jar

If the phalidator lib is used from java it's recommended to use the dist/lib/phalidator.jar which has no dependencies included.
The dependencies are in [ivy.xml](ivy.xml)

Targets
=======

Java Map validator using validation tree
----------------------------------------

This validator uses the resulting validation tree of the parser. It's not generated and uses the dependencies including antlr4 runtime.

[MapValidationExample.java](examples/java/src/ch/kerbtier/phalidator/examples/map/MapValidationExample.java)

Javascript obj validator
------------------------

Js code can be generated by invoking:

    java -jar dist/phalidator-cli.jar rules.phal js-obj obj.js
    
and will generate a obj.js file with the validation javascript code.

[Example js generator](examples/javascript/) (Build is needed before running)

    

