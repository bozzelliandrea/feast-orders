import 'package:commons/commons.dart';
import 'package:flutter/material.dart';
import 'package:orders/data/auth_base_service.dart';
import 'package:orders/data/auth_service.dart';
import 'package:orders/data/mock/mock_auth_service.dart';

@immutable
class RepositoryBundle {

  final AuthBaseService authService;

  RepositoryBundle({
    required ApiService apiService,
    required AuthSessionStorage authSessionStorage,
    required bool mock
  }) : authService = _buildAuthRepository(apiService: apiService, authSessionStorage: authSessionStorage, mock: mock);

  static AuthBaseService _buildAuthRepository({
    required ApiService apiService,
    required AuthSessionStorage authSessionStorage,
    required bool mock
  }) {
    if (mock) {
      return MockAuthService();
    } else {
      return AuthService(apiService: apiService, authSessionStorage: authSessionStorage);
    }
  }

}