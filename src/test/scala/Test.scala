import org.scalatest._
import spray.http._
import spray.httpx._
import spray.routing._
import spray.testkit._

class ServiceTest extends FunSpec with ShouldMatchers {
  it("returns a user id=1") {
    val target = new UserService {}
    target.get(1) shouldBe Some(User(1, "alice"))
  }
  it("returns none for missing ids") {
    val target = new UserService {}
    target.get(999) shouldBe None
  }
}

class RoutingTest extends FunSpec with ShouldMatchers with ScalatestRouteTest
  with HttpService with SprayJsonSupport with Route {
  def actorRefFactory = system

  it("returns 200 with user entity") {
    Get("/users/1") ~> route ~> check {
      status shouldBe StatusCodes.OK
      responseAs[User] shouldBe User(1, "alice")
    }
  }
  it("returns 404 when entity is missing") {
    Get("/users/999") ~> route ~> check {
      status shouldBe StatusCodes.NotFound
    }
  }
}
