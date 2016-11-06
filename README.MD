# Akka Actors by Examples

## Introduction

This is set of examples prepared for [Hack Your Career events](www.facebook.com/Hack.your.Career)


## Examples

### Example 1

This is very simple example of actors communicating with simple messages. This example was taken from Akka getting started description.
Actor `MainActor` creates a new actor `HelloActor` and sends message to SayHello. `HelloActor` responds with message `Done`.


### Example 2

This is more advanced example where we have `WorkerActor` which is performing factorial calculation and with have `CalculationManagerActor`
which responsibility is to create many `WorkerActors` and collect results of calculation.


### Example 3

This example is based on example 2 and adds additional features. In this example `WorkedActor` is returning Either with result of calculation or error.
There are two calculation cases where actor is simulating errors returning `CalculationError` or do not reply at all.

`CalculationManagerActor` is additionally extended with scheduler whcih is taking care for not waiting forever for calculation results.

## Usage

### Prerequisites

- Download and install [Scala](http://www.scala-lang.org/download/)
- Download and install [SBT](http://www.scala-sbt.org/download.html)

### Run examples

- Clone repository
- Compile repository using

```
sbt compile
```

- Start examples:
```
sbt run
```

and select which example will be started.


## Presentation

You can also check presentation prepared for `Hack Your Career` event. [Presentation](https://github.com/lszymik/actormodel)
