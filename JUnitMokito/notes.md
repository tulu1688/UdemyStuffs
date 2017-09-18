# Section 2: Introduction
- Why unit test
  - Assure quality
  - Run fast
  - Regression suite
- Unit testing: JUnit, Mockito
- Notes: When run unit test we test for public function only

# Section 3: JUnit in action
- Testing framework:
  - provide API to easily writing tests
  - provide ways to assert for the results
  - run and reports results
- JUnit:
  - JUnit3: junit.framework.TestCase
  - JUnit4: Annotations: `@Test`, `@Before` -> run function before every tests in class, `@After` run function after every tests in class, `@BeforeClass` run once at the beginning of class, `@Ignore`
  - `import static org.junit.Assert.*;`
  - Default JUnitest runner class: `BlockJunit4Runner.class`

## Testing for exception
example
```
@Test(expected = IllegalArgumentException.class)
public void greetShouldThrowAnException_For_NameIsNull(){
  Greeting greeting = new GreetingImpl();
  greeting.greet(null);
}
```

# Section 4: Mokito in action
- Mocking: Testing in isolation.
- 3 step in mock: stubbing, setting expectation -> verifying
- Mokito mocking framework
- Sample code is `OrderProcessingService`

## Add mockito dependency

```
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.9.5</version>
    <scope>test</scope>
</dependency>
```

## Stubbing and Setting expectation
- See example in `OrderProcessingService`

# Section 5: Code coverage
CodeCoverage Tool: EclEmma -> eclipse, IntelliJ build-in Code Coverage tool

# Section 6: More mockito
- Mockito matcher: any(), anyInt(), anyLong()...
- Mockito verification mode: atLeast, times
- More example in `ScrapBook project`

# Section 7: Best practices and Patterns
## The F.I.R.S.T principle
F: fast
I: independent
R: repeatable
S: self-validating
T: timely

## Test double
Test double is a design pattern used in unit testing framework

Parts of test double: Dummy, Stubs, Mocks, Fake, Spies

## Test double in Mockito
- Dummy: `any(Object.class)`
- Stubs: `when(...).return(...)`
- Mocks: exception, verify
- Fake: inmemory database used when mockito run the testcases
- Spies: partial mocking

## Partial mocking using mockito spy
- See ListTest.java in `OrderProcessingService` code

## Spy vs Mock
- Technically speaking both "mocks" and "spies" are a special kind of "test doubles".
- Mockito is unfortunately making the distinction weird.
  - A mock in mockito is a normal mock in other mocking frameworks (allows you to stub invocations; that is, return specific values out of method calls).
  - A spy in mockito is a partial mock in other mocking frameworks (part of the object will be mocked and part will use real method invocations)
- Typical use case for Spy: the class has a parameterized constructor, you want to create the object first.

## Maven and JUnit quickstart
- Some well-known maven archetype
  - `maven-archetype-quickstart`
  - `maven-archetype-webapp`

# Section 8: Parameterized JUnit
- Example: `calculator` project

# Section 9: Power mock
