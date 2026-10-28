# Practical-24 - Testing a Java Program Using JUnit

## Aim
Performs unit testing of a Calculator class using JUnit 5 assertions (assertEquals, assertThrows) and lifecycle annotations (@BeforeEach, @Test).

## Files
- `CalculatorTest.java`: Source code implementation.

## Compilation and Execution

```bash
# Compile
javac -cp ../lib/junit-platform-console-standalone-1.10.2.jar:. CalculatorTest.java

# Run
java -jar ../lib/junit-platform-console-standalone-1.10.2.jar execute --class-path . -c CalculatorTest
```

## Output
```text
Thanks for using JUnit! Support its development at https://junit.org/sponsoring

[36m╷[0m
[36m├─[0m [36mJUnit Jupiter[0m [32m✔[0m
[36m│  └─[0m [36mCalculatorTest[0m [32m✔[0m
[36m│     ├─[0m [34mtestDivideByZero()[0m [32m✔[0m
[36m│     ├─[0m [34mtestAdd()[0m [32m✔[0m
[36m│     ├─[0m [34mtestSubtract()[0m [32m✔[0m
[36m│     ├─[0m [34mtestDivide()[0m [32m✔[0m
[36m│     └─[0m [34mtestMultiply()[0m [32m✔[0m
[36m├─[0m [36mJUnit Vintage[0m [32m✔[0m
[36m└─[0m [36mJUnit Platform Suite[0m [32m✔[0m

Test run finished after 28 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         5 tests found           ]
[         0 tests skipped         ]
[         5 tests started         ]
[         0 tests aborted         ]
[         5 tests successful      ]
[         0 tests failed          ]
```
