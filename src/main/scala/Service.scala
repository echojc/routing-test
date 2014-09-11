import akka.actor._
import spray.json._

// response messages used by all services
case class Ok[T](t: T)
case object NotFound
//case class Validation(validations: List[???])
//etc.

//*** USER SERVICE ***//

object User extends DefaultJsonProtocol {
  implicit val jf = jsonFormat2(User.apply)
}
case class User(id: Int, name: String)

// actor interface
object UserServiceActor {
  case class Get(id: Int)
}
class UserServiceActor extends Actor {
  import UserServiceActor._

  def receive = {
    case Get(id) ⇒
      UserService.get(id) match {
        case Some(user) ⇒ sender ! Ok(user)
        case None       ⇒ sender ! NotFound
      }
  }
}

// actual service
trait UserService {
  def get(id: Int): Option[User] = id match {
    case 1 ⇒ Option(User(1, "alice"))
    case _ ⇒ None
  }
}
object UserService extends UserService
