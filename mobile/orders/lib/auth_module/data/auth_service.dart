import 'dart:convert';

import 'package:commons/commons.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:orders/auth_module/data/auth_base_service.dart';
import 'package:orders/auth_module/models/auth.dart';
import 'package:orders/auth_module/models/auth_response.dart';

@immutable
class AuthService extends AuthBaseService {

  final ApiService _apiService;
  final AuthSessionStorage _authSessionStorage;

  AuthService({
    required ApiService apiService,
    required AuthSessionStorage authSessionStorage
  }) : _apiService = apiService,
        _authSessionStorage = authSessionStorage;

  @override
  Future<AuthResponse> login(Auth auth) async {
    http.Response? response = await _apiService.api.auth.login.execute(dto: auth);
    if (response?.body == '') throw Exception('User not found');

    Map<String, dynamic> respJson = json.decode(response!.body);
    AuthResponse authResponse = AuthResponse.fromJson(respJson);

    await _authSessionStorage.saveToken(authResponse.token!);
    // await _authSessionStorage.saveUserInfo(authResponse);

    return authResponse;
  }

  @override
  Future<AuthResponse> register(Auth auth) async {
    http.Response? response = await _apiService.api.auth.register.execute(dto: auth);
    if (response!.body == '') throw Exception('');

    Map<String, dynamic> respJson = json.decode(response.body);
    AuthResponse authResponse = AuthResponse.fromJson(respJson);
    authResponse.signUpSuccessful = true;
    return authResponse;
  }

  @override
  Future<void> logout() async {
    return _authSessionStorage.deleteAll();
  }

}