package il.ac.hit.validation

class Invalid(val reason:String = "") extends ValidationResult {
  def isValid: Boolean = false

  override def getReason: Option[String] =
  {
    if(reason.isEmpty)
      None
    else
      Some(reason)
  }}