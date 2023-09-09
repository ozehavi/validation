package il.ac.hit.validation

/**
 * Represents an invalid result of a validation.
 * @param reason The reason for the validation failure (optional).
 */
class Invalid(val reason: String = "") extends ValidationResult {
  /**
   * Checks if the validation result is valid.
   * @return `false` since this represents an invalid result.
   */
  def isValid: Boolean = false

  /**
   * Gets the reason for the validation failure, if available.
   * @return An `Option` containing the reason if it exists, otherwise `None`.
   */
  override def getReason: Option[String] = {
    if (reason.isEmpty)
      None
    else
      Some(reason)
  }
}