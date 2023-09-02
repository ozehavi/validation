//package il.ac.hit.validation
//
//import java.util.function.Predicate
//
//sealed trait ValidationResult
//case class Valid() extends ValidationResult
//case class Invalid(message: String) extends ValidationResult
//
//trait UserValidationV2 extends (User => ValidationResult) {
//
//  def check(p: Predicate[User], message: String): UserValidationV2 =
//    user => if (p.test(user)) Valid() else Invalid(message)
//
//  def nameIsNotEmpty(): UserValidationV2 =
//    check(user => user.username.trim.isEmpty, "username is not empty")
//
//  def emailEndsWithIL(): UserValidationV2 =
//    check(user => user.email.endsWith(".il"), "email not ends with il")
//
//  def and(other: UserValidationV2): UserValidationV2 =
//    user => {
//      val result = this.apply(user)
//      if (result.isValid) other.apply(user) else result
//    }
//}
//
//object UserValidationV2 {
//  val instance: UserValidationV2 = new UserValidationV2 {
//    override def apply(user: User): ValidationResult = Valid() // Default implementation
//  }
//}
