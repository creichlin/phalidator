#!/bin/bash

java -jar /usr/share/java/antlr-4.2-complete.jar -package ch.kerbtier.phalidator.parser -visitor -no-listener src/ch/kerbtier/phalidator/parser/Phal.g4
