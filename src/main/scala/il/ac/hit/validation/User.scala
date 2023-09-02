package il.ac.hit.validation

class User(usernameP: String, emailP: String, passwordP: String, ageP: Int) {

  private var _username: String = "";
  private var _email: String = "";
  private var _password: String = "";
  private var _age: Int = 0;

  def username: String = _username;
  def email: String = _email;
  def password: String = _password;
  def age: Int = _age;

  username = usernameP
  email = emailP
  password = passwordP
  age = ageP


  def username_=(value: String):Unit =
  {
    _username = value;
  }

  def email_=(value: String):Unit =
  {
    _email = value;
  }

  def password_=(value: String):Unit =
  {
    _password = value;
  }

  def age_=(value: Int):Unit =
  {
    _age = value;
  }

  override def toString: String = s"User(username=$username, email=$email, age=$age)"
}