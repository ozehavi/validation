package il.ac.hit.validation

/**
 * A trait representing the result of a validation operation.
 */
trait ValidationResult {
  /**
   * Checks if the validation result is valid.
   * @return `true` if the validation result is valid, `false` otherwise.
   */
  def isValid: Boolean

  /**
   * Gets the reason for an invalid validation result.
   * @return An `Option` containing a reason message if the validation result is invalid, or `None` if it's valid.
   */
  def getReason: Option[String]
}