import 'package:flutter/material.dart';

@immutable
class Configuration {

  //localhost for AVD: http://10.0.2.2:8081/
  static const String baseUrl = "http://10.0.2.2:8081/api";
  static const bool useMockData = false;
  static const bool logging = true;

  static final _UserInfo userInfo = _UserInfo(
    username: 'bozzaccio',
    password: 'password',
    email: 'bozzaccio@hotmail.it',
    token: 'aaaaaaaaaa-bbbbbbbbb-ccccccccccc-ddddddddd'
  );

}

class _UserInfo {
  String? username;
  String? password;
  String? email;
  String? token;

  _UserInfo({ String? username, String? password, String? email, String? token })
    : username = username,
      password = password,
      email = email,
      token = token;
}
