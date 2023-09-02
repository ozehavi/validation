package il.ac.hit.validation

object Demo {
  val user1:User = new User("haim","haim.michael@gmail.il","abc123",33)
  val result1:ValidationResult = UserValidation.emailEndsWithIL.
    and(UserValidation.emailLengthBiggerThan10).apply(user1);
  print(result1.isValid);
  val result2:ValidationResult = UserValidation.emailEndsWithIL.
    or(UserValidation.emailLengthBiggerThan10).apply(user1);
  print(result2.isValid);
}