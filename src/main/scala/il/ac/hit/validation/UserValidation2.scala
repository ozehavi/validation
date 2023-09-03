package il.ac.hit.validation


// Define the UserValidation trait
trait UserValidation2 extends (User => ValidationResult) {
  def and(other: UserValidation): UserValidation = (user) => {
    val thisResult = this(user)
    val otherResult = other(user)
    if (thisResult.isValid && otherResult.isValid) new Valid
    else if (thisResult.isValid) otherResult
    else thisResult
  }

  def or(other: UserValidation): UserValidation = (user) => {
    val thisResult = this(user)
    val otherResult = other(user)
    if (thisResult.isValid || otherResult.isValid) new Valid
    else new Invalid(s"Neither condition met: ${thisResult.getReason.getOrElse("")}, ${otherResult.getReason.getOrElse("")}")
  }
}

// Define the UserValidation object
object UserValidation2 extends UserValidation {
  def apply(user: User): ValidationResult = new Valid // Default, always valid

  def all(validations: UserValidation*): UserValidation = (user) => {
    val results = validations.map(validation => validation(user))
    if (results.forall(_.isValid)) new Valid
    else {
      val reasons = results.flatMap(result => result.getReason)
      new Invalid(reasons.mkString(", "))
    }
  }

  def none(validations: UserValidation*): UserValidation = (user) => {
    val results = validations.map(validation => validation(user))
    if (results.forall(!_.isValid)) new Valid
    else {
      val reasons = results.flatMap(result => result.getReason)
      new Invalid(s"At least one condition is met: ${reasons.mkString(", ")}")
    }
  }

  def emailEndsWithIL: UserValidation = (user) =>
    if (user.email.endsWith("il")) new Valid
    else new Invalid("Email does not end with 'il'")

  def emailLengthBiggerThan10: UserValidation = (user) =>
    if (user.email.length > 10) new Valid
    else new Invalid("Email length is not bigger than 10")

  def passwordLengthBiggerThan8: UserValidation = (user) =>
    if (user.password.length > 8) new Valid
    else new Invalid("Password length is not bigger than 8")

  def passwordIncludesLettersNumbersOnly: UserValidation = (user) =>
    if (user.password.matches("^[a-zA-Z0-9]+$")) new Valid
    else new Invalid("Password does not include letters and numbers only")

  def passwordIncludesDollarSign: UserValidation = (user) =>
    if (user.password.contains("$")) new Valid
    else new Invalid("Password does not include the $ character")

  def passwordIsDifferentFromUsername: UserValidation = (user) =>
    if (user.password != user.username) new Valid
    else new Invalid("Password is the same as the username")

  def ageBiggerThan18: UserValidation = (user) =>
    if (user.age > 18) new Valid
    else new Invalid("Age is not bigger than 18")

  def usernameLengthBiggerThan8: UserValidation = (user) =>
    if (user.username.length > 8) new Valid
    else new Invalid("Username length is not bigger than 8")
}
