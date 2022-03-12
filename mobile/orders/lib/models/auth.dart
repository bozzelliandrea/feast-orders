import 'package:commons/commons.dart';
import 'package:flutter/material.dart';

@immutable
class Auth extends AbstractModel {

  final String username;
  final String password;
  final String? email;

  Auth(this.username, this.password, this.email);

  @override
  List<Object?> get props => [username, email];

  @override
  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['username'] = username;
    data['password'] = password;
    if (email != null) {
      data['email'] = email;
    }
    return data;
  }

}