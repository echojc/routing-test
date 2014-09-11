import akka.actor._
import scala.reflect.ClassTag
import spray.http._
import spray.httpx._
import spray.json._
import spray.routing._

//*** CONTEXT ACTOR ***//
// T _must_ have a RootJsonFormat which indicates it's top level JSON
class Context[T](val ctx: RequestContext, val props: Props, val message: Any)
  (implicit ct: ClassTag[T], rjf: RootJsonFormat[T]) extends Actor with SprayJsonSupport {

  val target = context.actorOf(props)
  target ! message

  def receive = {
    // ClassTag on T means that this matches correctly
    case Ok(t: T) ⇒
      ctx.complete(StatusCodes.OK, t)
    case NotFound ⇒
      ctx.complete(StatusCodes.NotFound)
  }
}
