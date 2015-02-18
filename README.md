GpsAlarm
========

TDD practice

This is a project I'm developing as practice of TDD and the architecture presented by Robert Martin in Clean Code.

The core idea is to separate the implementation details (platform, data storage, user interface, etc.) from the actual logic and bussines rules of the application. This is achieved by letting the use cases drive the architecture; each use case is implemented by an Interactor object, and the controllers can only call Interactors and not the actual objects related to the problem. The Interactors implement the business rules, and call the problem entities when they have to resolve something internal to them. Interactors methods can only return or receive as parameter Data Objects without real functionality, so the classes that depend on them are truly incapable of calling anything other than their use cases.

Ideally, this will result on an interface that's very testable, in which implementation details can be defered until very late in the development process.


The project itself is a simple Android app that let's you place location alarms in a map and rings when you enter a marked area.
