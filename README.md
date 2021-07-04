# Locadora
## PoS for Movie Rental Service

[![Scrutinizer Code Quality](https://scrutinizer-ci.com/g/Mineirovsky/locadora/badges/quality-score.png?b=master)](https://scrutinizer-ci.com/g/Mineirovsky/locadora/?branch=master)
[![Build Status](https://scrutinizer-ci.com/g/Mineirovsky/locadora/badges/build.png?b=master)](https://scrutinizer-ci.com/g/Mineirovsky/locadora/build-status/master)

This is a GUI point-of-sale for the counter of a movie rental shop. It can
register customers, handle a catalogue of VHSs, DVDs and Blu-ray Discs, perform rentals and check for late customers.

This project started as a college project that should run on the terminal. But I
took the opportunity to deepen my knowledge on Java, its libraries and tools.
So I gathered the knowledge I've acquired from projects in other languages and
tried my best to do something industry level and still comply with the rules
imposed by the professor.

I will not attach the document with the professor's requirements because it
might infringe his and the college's copyright. But basically they require that
I use the file system, so no JDBC and the classes must be POJO. The professor
has suggested that I use CSV as the format for persistance, so I went with it and implemented a parser and formatter.

## Tech Stack
Because the goal is to develop intimacy with Java, I decided to use no framework
and deal with MVC by myself &mdash; a good way to test many patterns
implementations in the language. In the end, only a few tools and libraries were
used:

- Swing
- Maven
- JUnit Jupiter
- Scrutinizer CI
