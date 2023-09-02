package il.ac.hit.validation

// Define the UserValidation trait that extends Function1[User, ValidationResult]
trait UserValidation extends (User => ValidationResult)

object UserValidation extends UserValidation {
  def apply(user: User): ValidationResult = {

    if (user.username.nonEmpty && user.email.contains("@")) {
      Valid
    } else {
      Invalid("Invalid username or email")
    }
  }
}

;