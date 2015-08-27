# In development

First run `sbt`.

Run `~re-start`.
Open `localhost:8080` in browser.
Refresh the browser manually.

Run tests with `test`.

Run specific tests with `test-only foo.bar.MyTest1 foo.bar.MyTest2`.


# In production

Run `sbt` then `appJVM/run`.
Open `localhost:8080` in browser.


# IntelliJ

IntelliJ does not support referencing to the shared directory. But this is just the IDE. The compiler understands the shared code. So it works as it is supposed to.
