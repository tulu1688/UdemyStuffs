# Creational design patterns
## 1. Singleton
- One object at runtime

## 2. Builder
- Build complex objects
- Benefits:
+ One build process for multiple similar objects
+ Uniform production creation via an Interface
+ Abstract building process: 1 building process -> multiple type of products

## 3. Abstract factory
- Create object families
- Intent: provide an interface for creating families of related or dependent objects without specifying their concrete classes.
- Known as: Kit

## 4. Factory method
- Delegate object creation
- Intent: define an interface for creating an object but let the subclass decide which class to instantiate.
- Known as ~~~Virtual Constructor~~~

## 5. Prototype
- Clone the objects
- Intent: specify the type of objects to create using a prototypical instance , and create new objects by copying this prototype.

## Notes
- Builder focuses on constructing a complex object step by step. Abstract Factory emphasizes a family of product objects (either simple or complex). Builder returns the product as a final step, but as far as the Abstract Factory is concerned, the product gets returned immediately.
- Builder often builds a Composite.
- Often, designs start out using Factory Method (less complicated, more customizable, subclasses proliferate) and evolve toward Abstract Factory, Prototype, or Builder (more flexible, more complex) as the designer discovers where more flexibility is needed.
- Sometimes creational patterns are complementary: Builder can use one of the other patterns to implement which components get built. Abstract Factory, Builder, and Prototype can use Singleton in their implementations.

# Structural design patterns
## 1. Facade
- Interface for subsystem. Facade define the high-level interface that makes the subsystem easier to use. Works as the subsystem access point, delegates to subsystem.
- Client --> Facade --> Subsystem
- Subsystem can be used directly if necessary
- Drawback: Facade need extra programming layer

## 2. Adapter
- Adapt to existing systems. It allows client connect to legacy system
- Also know as wrapper
- Drawback: adapter doesn't work for class with many subclasses (Some programming language not support multiple inherit). Harder to override adaptee behaviors (Do we need it?)

## 3. Decorate
- Dynamically add functionality
- Decorate pattern provide flexible to subclassing for extending functionality
- Known as wrapper. (Adapter also known as wrapper also. But it usually for legacy subsystem)
- Use when add functionality to objects without affecting other objects.

## 4. Bridge
- Separate implementation from interface (abstraction)
- Known as Handle, Body
- Improve extensibility
- Reduce number of subclass

## 5. Composite
- Combine multiple objects
- Compose objects into tree structures. Lets the client treat the individual objects and compositions of objects uniformly.
- Use when ignore the differences between the compositions and individual items.
- Represent part-whole hierarchies of objects.

## 6. Proxy
- Object placeholder
- Known as surrogate
- Extra function must be transparency

## 7. Flyweight
- Efficiently manage/share objects
- Use sharing to support large numbers of fine-grained objects efficiently
- Use Flyweight when: large number of objects, high storage costs, object can be shareable, object identity isn't necessary.

# Behaviors pattern
## 1. Command
- Capture user actions
- Encapsulate a request as an object. Let you parameterize client with differences requests, queue, log requests and support undoable operations.
- Known as: Action, Transaction
- Conclusion: capture a request, centralize action functionality

## 2. Strategy
- Define a family of algorithm. Let the algorithm independently from clients that use it.
- Encapsulate real life algorithm, change algorithm at run-time
- Known as policy

## 3. Visitor
- Visit object tree
- Represent an operation to be performed on the elements of an object structure. Visitor let define new operation without changing the classes of the elements on which it operate.
- Use when visit complex object structure.
- Drawback: visitors break encapsulation

## 4. Observer
- Observe changes, states of the other objects
- Support a broadcast model
- Drawback: one change can result in unnecessary notifications

## 5. Iterator
- Iterate over object collection

## 6. Memento
- Memorize state "use When we need to remember the original state"
- Benefits: preserve encapsulation
- Drawback: might be expensive

## 7. Mediator
- Define object interaction
- Define an object to manage the interaction of a set of objects
- Use when have complex communication
- Benefits: change many-to-many -> one-to-many, centralize behavior

## 8. Change of responsibility
- Chain object request

## 9. State
- Change behavior based on state

## 10. Template method - Interpreter
- Grammar language

# Other templates
## Inversion of control
- Let object pass a dependent object to another object
- Allow a system to release control of object creation by allowing the other components to manage creation of the objects and its dependencies.
