package il.ac.hit.validation

trait UserValidation extends Function1[User, ValidationResult]{
//  def and(other: UserValidation): UserValidation = (user: User) => {
//    val result1 = this(user)
//    val result2 = other(user)
//    if (result1.isValid && result2.isValid)
//      new Valid()
//    else
//      new Invalid(s"Validation failed: $result1 and $result2")
//  }
//  def or(other: UserValidation): UserValidation = (user: User) => {
//    val result1 = this(user)
//    val result2 = other(user)
//    if (result1.isValid || result2.isValid)
//      new Valid()
//    else
//      new Invalid(s"Validation failed: $result1 or $result2")
//  }

//  def apply(user: User): ValidationResult
//
  def and(other: UserValidation): UserValidation = (user: User) =>
  {
    if(this.apply(user).isValid && other.apply(user).isValid)
      new Valid()
    else
      new Invalid("and failed")
  }
//
//  def or(other: UserValidation): UserValidation = (user: User) => this.apply(user) || other.apply(user)
}

object UserValidation{
//  def apply(): Boolean = {
//
//  }

  def emailEndsWithIL(): UserValidation = {
    (user: User) => {
      if(user.email.nonEmpty && user.email.length > 2 && user.email.takeRight(3) == ".il")
        new Valid()
      else
        new Invalid("email not ends with il")
    }
  }

}

