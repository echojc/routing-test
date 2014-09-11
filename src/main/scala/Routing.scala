import akka.actor._
import scala.reflect.ClassTag
import spray.json._
import spray.routing._

trait Route { _: HttpService ⇒
  def route =
    get {
      path("users" / IntNumber) { id ⇒ ctx ⇒
        actorRefFactory.actorOf {
          // [return type](context, props for service, message to pass to service actor)
          PropsGen.returning[User](ctx, Props[UserServiceActor], UserServiceActor.Get(id))
        }
      }
    }
}

object PropsGen {
  def returning[T](ctx: RequestContext, target: Props, message: Any)
    (implicit ct: ClassTag[T], rjf: RootJsonFormat[T]): Props = {
    Props(classOf[Context[T]], ctx, target, message, ct, rjf)
  }
}
