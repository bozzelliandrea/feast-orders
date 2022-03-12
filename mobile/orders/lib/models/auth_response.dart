class AuthResponse {

  // sign in
  String? token;
  String? type;
  int? id;
  String? username;
  String? email;
  // List<String>? roles; fixme

  // sign up
  bool? signUpSuccessful;

  AuthResponse();

  AuthResponse.fromJson(Map<String, dynamic> json) {
    token = json['token'];
    type = json['type'];
    id = json['id'];
    username = json['username'];
    email = json['email'];
    // roles = json['roles'];
  }

}