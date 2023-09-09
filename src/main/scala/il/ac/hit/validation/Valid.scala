package il.ac.hit.validation

/**
 * Represents a validation result that is considered valid.
 * @param reason an optional reason for the validation result.
 */
class Valid(val reason: String = "") extends ValidationResult {
  /**
   * Checks if the validation result is valid.
   * @return true since this represents a valid result.
   */
  override def isValid: Boolean = true

  /**
   * Gets the reason for the validation result, if available.
   * @return an Option containing the reason if it's non-empty, otherwise None.
   */
  override def getReason: Option[String] =
  {
    if(reason.isEmpty)
      None
    else
      Some(reason)
  }
}