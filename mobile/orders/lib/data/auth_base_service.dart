import 'package:flutter/material.dart';
import 'package:orders/models/auth.dart';
import 'package:orders/models/auth_response.dart';
import 'package:orders/repositories/service.dart';

@immutable
abstract class AuthBaseService extends Service<Auth, AuthResponse> {

  Future<AuthResponse> login(Auth auth);

  Future<AuthResponse> register(Auth auth);

  Future<void> logout();

}