package com.hektorKS.Creational.DependencyInjection;

import com.hektorKS.Creational.Singleton.Logger;

/**
 *Basically, instead of having your objects creating a dependency or asking a factory object to make one for them,
 * you pass the needed dependencies in to the object externally, and you make it somebody else's problem.
 * This "someone" is either an object further up the dependency graph, or a dependency injector (framework)
 * that builds the dependency graph. A dependency as I'm using it here is any other object the current object needs
 * to hold a reference to.
 *
 * One of the major advantages of dependency injection is that it can make testing lots easier.
 * Suppose you have an object which in its constructor does something like:
 *
 * public SomeClass() {
 * myObject = Factory.getObject();
 * }
 *
 * This can be troublesome when all you want to do is run some unit tests on SomeClass, especially if myObject
 * is something that does complex disk or network access. So now you're looking at mocking myObject but also
 * somehow intercepting the factory call. Hard. Instead, pass the object in as an argument to the constructor.
 * Now you've moved the problem elsewhere, but testing can become lots easier. Just make a dummy myObject and pass
 * that in. The constructor would now look a bit like:
 *
 * public SomeClass (MyClass myObject) {
 * this.myObject = myObject;
 * }
 *
 * This is one style of dependency injection - via the constructor. Several mechanisms are possible.
 * 1. As noted in the comments, one common alternative is to define a do-nothing constructor,
 * and have the dependencies injected via property setters (h/t @MikeVella).
 * 2. Martin Fowler documents a third alternative (h/t @MarcDix), where classes explicitly implement an interface
 * for the dependencies they wish injected.
 *
 */
public class DependencyInjectionTester {

    private StringBuilder log;

    {
        log = new StringBuilder();
        log.append("Dependency Injection design pattern tests\n");
        log.append("This design pattern is not in GOF book\n\n");
    }

    public void test(){

    Session s = new Session("SuperSession");
    User u = new User(s);

    log.append("Session's name: ");
    log.append(u.getName());
    log.append("\n");

    u.setName("NewSuperSession");

    log.append("New session's name: ");
    log.append(u.getName());
    log.append("\n");

    log.append("-----------------------------------------------------------------------------------------------\n");

    Logger.INSTANCE.addToLog(log.toString());

    }
}
