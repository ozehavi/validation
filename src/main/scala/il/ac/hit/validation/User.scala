package il.ac.hit.validation

/**
 * Represents a User with properties such as username, email, password, and age.
 *
 * @param userNameP The username of the user.
 * @param emailP    The email address of the user.
 * @param passwordP The password of the user.
 * @param ageP      The age of the user.
 */
class User(userNameP: String, emailP: String, passwordP: String, ageP: Int) {

  // Private fields to store user information
  private var _userName: String = "";
  private var _email: String = "";
  private var _password: String = "";
  private var _age: Int = 0;

  /**
   * Get the username of the user.
   * @return The username as a String.
   */
  def userName: String = _userName;
  /**
   * Get the email address of the user.
   * @return The email address as a String.
   */
  def email: String = _email;
  /**
   * Get the password of the user.
   * @return The password as a String.
   */
  def password: String = _password;
  /**
   * Get the age of the user.
   * @return The age as an integer.
   */
  def age: Int = _age;

  // Set the initial values for user properties
  userName = userNameP
  email = emailP
  password = passwordP
  age = ageP

  /**
   * Set the username of the user.
   * @param value The new username to set.
   * required: the provided username contains only English letters and numbers and is not empty.
   */
  def userName_=(value: String):Unit =
  {
    require(value.nonEmpty && value.matches("^[a-zA-Z0-9]+$"), "User name must contain only English letters and numbers and not be empty.")
    _userName = value
  }

  /**
   * Sets the user's email address.
   *
   * @param value the new email address to set.
   * required: the provided email address is valid and is not empty.
   */
  def email_=(value: String): Unit = {
    require(value.nonEmpty && isValidEmail(value), "Email must be in a valid email format and not be empty.")
    _email = value
  }

  /**
   * Set the password of the user.
   * @param value The new password to set.
   * required: the provided password meets the required format and is not empty.
   */
  def password_=(value: String): Unit = {
    require(value.nonEmpty && isValidPassword(value), "Password must be 6 characters long and not empty.")
    _password = value
  }

  /**
   * Sets the user's age.
   *
   * @param value the new age to set.
   * required: the provided age is within a valid range.
   */
  def age_=(value: Int): Unit = {
    require(isValidAge(value), "Age must be a number between 0-120")
    _age = value
  }

  /**
   * Checks if the given string is a valid email address.
   *
   * @param email the email address to validate.
   * @return true if the email address is valid, false otherwise.
   */
  private def isValidEmail(email: String): Boolean = {
    // Regular expression for a simple email validation
    val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"
    email.matches(emailRegex)
  }

  /**
   * Validates a user's password against a minimum length requirement.
   *
   * @param password the password to validate.
   * @return `true` if the password meets the minimum length requirement, `false` otherwise.
   */
  private def isValidPassword(password: String): Boolean = {
    password.length >= 6 // Minimum length of 6 characters
  }

  /**
   * Checks if the provided age is within a valid range.
   *
   * @param age the age value to validate.
   * @return `true` if the age is within a valid range, `false` otherwise.
   */
  private def isValidAge(age: Int): Boolean = {
    val minValidAge = 0
    val maxValidAge = 120
    age >= minValidAge && age <= maxValidAge
  }

  /**
   * Returns a string representation of the User object.
   * @return A string in the format "User(username=..., email=..., age=...)".
   */
  override def toString: String = s"User(userName=$userName, email=$email, age=$age)"
}