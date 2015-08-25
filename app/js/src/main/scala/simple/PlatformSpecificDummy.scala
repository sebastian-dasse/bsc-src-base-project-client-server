package simple

object PlatformSpecificDummy {
  def sayHello() = SharedDummy.sayHello()
}
