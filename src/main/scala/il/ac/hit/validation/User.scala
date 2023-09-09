package il.ac.hit.validation

/**
 * Custom exception classes for handling various invalid user data scenarios:
 *
 * - [[InvalidUserName]]: Represents an invalid user name.
 * - [[InvalidEmailException]]: Represents an invalid email address.
 * - [[InvalidPassword]]: Represents an invalid password.
 * - [[InvalidAgeException]]: Represents an invalid age value.
 *
 * Each exception provides a descriptive error message to help identify the specific issue.
 */
case class InvalidUserName(message: String) extends Exception(message)
case class InvalidEmailException(message: String) extends Exception(message)
case class InvalidPassword(message: String) extends Exception(message)
case class InvalidAgeException(message: String) extends Exception(message)

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
   * @throws InvalidUserName if the provided username does not contain only English letters and numbers or if it is empty.
   */
  def userName_=(value: String):Unit =
  {
    if (value.isEmpty) {
      throw InvalidUserName("Username cannot be empty.")
    } else if (value.matches("^[a-zA-Z0-9]+$")) {
      _userName = value
    } else {
      throw InvalidUserName("Username must contain only English letters and numbers.")
    }
  }

  /**
   * Sets the user's email address.
   *
   * @param value the new email address to set.
   * @throws InvalidEmailException if the provided email address is not valid or if is empty.
   */
  def email_=(value: String): Unit = {
    if (value.isEmpty) {
      throw InvalidEmailException("Email cannot be empty.")
    } else if (isValidEmail(value)) {
      _email = value
    } else {
      throw InvalidEmailException("Invalid email address format.")
    }
  }

  /**
   * Set the password of the user.
   * @param value The new password to set.
   * @throws InvalidPassword if the provided password does not meet the required format or if it is empty.
   */
  def password_=(value: String): Unit = {
    if (value.isEmpty) {
      throw InvalidEmailException("Password cannot be empty.")
    } else if (isValidPassword(value)) {
      _password = value
    } else {
      throw InvalidPassword("Invalid password format.")
    }
  }

  /**
   * Sets the user's age.
   *
   * @param value the new age to set.
   * @throws IllegalArgumentException if the provided age is not within a valid range.
   */
  def age_=(value: Int): Unit = {
    if (isValidAge(value)) {
      _age = value
    } else {
      throw InvalidAgeException("Invalid age value. Age must be within 0-120")
    }
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