# Object-oriented Programming - Dependency Injection (concrete classes)

## Learning Objectives
- Use dependency injection to improve extensibility of a program
- Explain the drawbacks of creating instances of classes inside other classes

## Set up instructions
- Fork this repository and clone the forked version to your machine
- Open the root directory of the project in IntelliJ

## Introduction

When a class uses an instance of another class, we call that dependency. When Class A uses a method that exists on Class B, Class A is dependent on Class B existing and actually providing the method being used. Class B is the dependency.

Consider the following pseudocode:

```java
class Car {
    public void accelerate() {
        Engine engine = new Engine();
        engine.ignite();
        engine.injectFuel();
    }
}

class Program {
    public static void main() {
        Car car = new Car();
        car.accelerate();
        car.accelerate();
    }
}
```

Every time `car.accelerate()` is called, the car creates a new instance of the engine class, triggers the ignition, and then injects fuel to make it go. Imagine having to give your car a brand-new engine every time a traffic light turned green! The `Car` class is dependent on the `Engine` class.

We can make a simple improvement by moving the engine creation to the car's constructor so it only creates one instance to be used:

```java
class Car {
    Engine engine;
    
    public Car() {
        this.engine = new Engine();
    }
    
    public void accelerate() {
        this.engine.ignite();
        this.engine.injectFuel();
    }
}
```

This is a step in the right direction. At least the car will only ever use one instance of an engine and not replace it every time it accelerates... but what if the engine breaks? What if we need to repair it? As it is right now, we would have to add a method to the car class to repair the engine:

```java
class Car {
    Engine engine;
    
    public Car() {
        this.engine = new Engine();
    }
    
    public void accelerate() {
        this.engine.ignite();
        this.engine.injectFuel();
    }
    
    public void repairEngine() {
        this.engine = new Engine();
    }
}
```

This is quickly breaking one of the earlier rules of OOP: encapsulation. The car class is doing more than it should! This is where *Dependency Injection* can help. We can provide dependencies for classes to use to enable managing them better; we can *inject* dependencies into classes. To do this, we can use a class constructor in the same way we define method parameters:

```java
class Engine {
    public void repair() {}
}

class Car {
    Engine engine;
    
    public Car(Engine engine) {
        this.engine = engine;
    }
    
    public void accelerate() {
        this.engine.ignite();
        this.engine.injectFuel();
    }
}
```

The Car class is still dependent on the Engine class, but it's a lot easier to manage that dependency now. We can provide the car with any instance of the engine class that we want to (it could have a 2 litre capacity, 1.5 litre, anything we like). Additionally, the car is no longer responsible for providing a method to repair the engine.

```java
class Program {
    public static void main() {
        Engine engine = new Engine();
        Car car = new Car(engine);
        
        car.accelerate();
        car.accelerate();
        
        engine.repair();
        
        car.accelerate();
    }
}
```

## Exercise

In the `./src/main/java/com/booleanuk/core` directory are a number of classes. The `Computer` class has some dependencies which it is hard coding inside its methods. Your task is to refactor the Computer class to make the tests pass.

The tests are in the `./src/test/java/com/booleanuk/core` directory. Don't change them, but you can use them as a guide to inform your decisions.

When all the tests pass, the exercise is complete.

## Extension

Refactor the classes and any tests that use them so that they exhibit encapsulation and abstraction more fully and add any other improvements that you can think of.

## Next Steps

Now that you've practice dependency injection, apply this knowledge to your ongoing Bob's Bagels OOP exercise. You should have multiple classes working together in that exercise; any dependencies should be injected rather than hard coded. You can inject via constructors and via method parameters.

## Test Output

![](./assets/run-a-test.PNG)

When you run a test, it's either going to pass or fail. When it fails, you'll be presented with a big red stream of text. This is called a stack trace and, though intimidating, does contain some useful information.

One of the core skills of a developer is debugging stack traces like this. The stack trace details in which classes & files the failure happened, and gives you a line number at the end. Most of the lines in the stack trace are irrelevant most of the time, you want to try and identify the files that you're actually working with.

In the sample screenshot below, we've tried to complete the first step of the exercise but provided an invalid value. Then we run the test associated with it and we see a big red stack trace, a test failure.

At the top, we see `expected: <32> but was: <33>`. This means the test expected the value to be 32, but the value the student provided was 33. We can see this in the code snippets at the top of the screenshot.

In the stack trace itself, we see this line: `at app//com.booleanuk.core.ComputerTest.shouldBeAged32(ExerciseTest.java:20)`. This is helpful! This tells us the exact line in the ExerciseTest.java file (line 20) where the failure happened, as well as the method name (shouldBeAged32), helping us to identify where the issue began. This is the kind of thing you need to look for; a relevant file name, method name, class name and line number to give you a good starting point for debugging.

![](./assets/test-failure.PNG)
