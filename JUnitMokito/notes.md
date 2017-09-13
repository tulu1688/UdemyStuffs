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
  - JUnit4: Annotations: `@Test`, `@Before`, `@After`, `@BeforeClass`, `@Ignore`
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
