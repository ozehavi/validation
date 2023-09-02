package il.ac.hit.validation

class Invalid(val errorMessage: String) extends ValidationResult {
  def isValid: Boolean = false

  override def getReason: Option[String] = ???
}