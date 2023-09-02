package il.ac.hit.validation

class Valid(val reason:String = "") extends ValidationResult {
  override def isValid: Boolean = true

  override def getReason: Option[String] =
  {
    if(reason.isEmpty)
      None
    else
      Some(reason)
  }

}
