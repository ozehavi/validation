package il.ac.hit.validation

class User(val username: String, val email: String, val password: String, val age: Int) {
  override def toString: String = s"User(username=$username, email=$email, age=$age)"
}