import 'package:flutter/material.dart';
import 'package:orders/configuration.dart';
import 'package:orders/auth_module/data/auth_base_service.dart';
import 'package:orders/auth_module/models/auth.dart';
import 'package:orders/auth_module/models/auth_response.dart';

@immutable
class MockAuthService extends AuthBaseService {

  @override
  Future<AuthResponse> login(Auth auth) async {
    await Future.delayed(const Duration(seconds: 3));
    AuthResponse authResponse = AuthResponse();
    authResponse.username = Configuration.userInfo.username;
    authResponse.email = Configuration.userInfo.email;
    authResponse.token = Configuration.userInfo.token;

    return authResponse;
  }

  @override
  Future<AuthResponse> register(Auth auth) async {
    await Future.delayed(const Duration(seconds: 3));
    AuthResponse authResponse = AuthResponse();
    authResponse.signUpSuccessful = true;
    return authResponse;
  }

  @override
  Future<void> logout() async {
    // noop
    await Future.delayed(const Duration(seconds: 3));
  }

}