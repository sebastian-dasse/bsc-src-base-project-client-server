package simple

import utest._

object DummyTest extends TestSuite {
  val tests = TestSuite{
    "the dummy should say hello" - {
      assert(PlatformSpecificDummy.sayHello() == "hello") // works but IntelliJ does not understand
    }
  }
}
