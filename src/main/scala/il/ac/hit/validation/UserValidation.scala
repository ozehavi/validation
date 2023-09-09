package il.ac.hit.validation

/**
 * Define the UserValidation trait.
 */
trait UserValidation extends (User => ValidationResult) {
  /**
   * Combines this UserValidation with another UserValidation using a logical AND operation.
   *
   * @param other The other UserValidation to combine with.
   * @return A new UserValidation that represents the logical AND of this and the other UserValidation.
   */
  def and(other: UserValidation): UserValidation = (user) => {
    val thisResult = this(user)
    val otherResult = other(user)
    if (thisResult.isValid && otherResult.isValid) new Valid
    else if (thisResult.isValid) otherResult
    else thisResult
  }

  /**
   * Combines this UserValidation with another UserValidation using a logical OR operation.
   *
   * @param other The other UserValidation to combine with.
   * @return A new UserValidation that represents the logical OR of this and the other UserValidation.
   */
  def or(other: UserValidation): UserValidation = (user) => {
    val thisResult = this(user)
    val otherResult = other(user)
    if (thisResult.isValid || otherResult.isValid) new Valid
    else new Invalid(s"Neither condition met: ${thisResult.getReason.getOrElse("")}, ${otherResult.getReason.getOrElse("")}")
  }
}

/**
 * Define the UserValidation object
 */
object UserValidation extends UserValidation {
  /**
   * This method is used to validate a User object using the implemented validation rules.
   *
   * @param user The User object to be validated.
   * @return A ValidationResult representing the result of the validation.
   */
  def apply(user: User): ValidationResult = new Valid // Default, always valid

  /**
   * Combines multiple UserValidation instances and checks if they all are valid.
   *
   * @param validations The UserValidation instances to combine.
   * @return A new UserValidation that represents the logical AND of all validations.
   */
  def all(validations: UserValidation*): UserValidation = (user) => {
    val results = validations.map(validation => validation(user))
    if (results.forall(_.isValid)) new Valid
    else {
      val reasons = results.flatMap(result => result.getReason)
      new Invalid(reasons.mkString(", "))
    }
  }

  /**
   * Combines multiple UserValidation instances and checks if they all are not valid.
   *
   * @param validations The UserValidation instances to combine.
   * @return A new UserValidation that represents the logical OR of all validations.
   */
  def none(validations: UserValidation*): UserValidation = (user) => {
    val results = validations.map(validation => validation(user))
    if (results.forall(!_.isValid)) new Valid
    else {
      val reasons = results.flatMap(result => result.getReason)
      new Invalid(s"At least one condition is met: ${reasons.mkString(", ")}")
    }
  }

  /**
   * Checks if the email of a user ends with "il".
   *
   * @return a UserValidation instance
   */
  def emailEndsWithIL: UserValidation = (user) =>
    if (user.email.endsWith("il")) new Valid
    else new Invalid("Email does not end with 'il'")

  /**
   * Checks if the length of the email of a user is greater than 10.
   *
   * @return a UserValidation instance
   */
  def emailLengthBiggerThan10: UserValidation = (user) =>
    if (user.email.length > 10) new Valid
    else new Invalid("Email length is not bigger than 10")

  /**
   * Checks if the length of the password of a user is greater than 8.
   *
   * @return a UserValidation instance
   */
  def passwordLengthBiggerThan8: UserValidation = (user) =>
    if (user.password.length > 8) new Valid
    else new Invalid("Password length is not bigger than 8")

  /**
   * Checks if the password of a user contains only letters and numbers.
   *
   * @return a UserValidation instance
   */
  def passwordIncludesLettersNumbersOnly: UserValidation = (user) =>
    if (user.password.matches("^[a-zA-Z0-9]+$")) new Valid
    else new Invalid("Password does not include letters and numbers only")

  /**
   * Checks if the password of a user contains the '$' character.
   *
   * @return a UserValidation instance
   */
  def passwordIncludesDollarSign: UserValidation = (user) =>
    if (user.password.contains("$")) new Valid
    else new Invalid("Password does not include the $ character")

  /**
   * Checks if the password of a user is different from their username.
   *
   * @return a UserValidation instance
   */
  def passwordIsDifferentFromUsername: UserValidation = (user) =>
    if (user.password != user.userName) new Valid
    else new Invalid("Password is the same as the username")

  /**
   * Checks if the age of a user is greater than 18.
   *
   * @return a UserValidation instance
   */
  def ageBiggerThan18: UserValidation = (user) =>
    if (user.age > 18) new Valid
    else new Invalid("Age is not bigger than 18")

  /**
   * Checks if the length of the username of a user is greater than 8.
   *
   * @return a UserValidation instance
   */
  def usernameLengthBiggerThan8: UserValidation = (user) =>
    if (user.userName.length > 8) new Valid
    else new Invalid("Username length is not bigger than 8")
}