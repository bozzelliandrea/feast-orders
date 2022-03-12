import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class AuthSessionStorage {
  static final AuthSessionStorage _instance =
      AuthSessionStorage._(const FlutterSecureStorage());

  factory AuthSessionStorage() => _instance;

  AuthSessionStorage._(this._storage);

  final FlutterSecureStorage _storage;

  static const _tokenKey = 'AUTH_TOKEN';
  static const _userInfoKey = 'AUTH_USER_INFO';

  Future<void> saveToken(String token) async {
    await _storage.write(key: _tokenKey, value: token);
  }

  Future<void> saveUserInfo(dynamic userInfo) async {
    // fixme
    String userInfoEncoded = jsonEncode(userInfo);
    await _storage.write(key: _userInfoKey, value: userInfoEncoded);
  }

  Future<bool> hasToken() async {
    var value = await _storage.read(key: _tokenKey);
    return value != null;
  }

  Future<bool> hasUserInfo() async {
    var value = _storage.read(key: _userInfoKey);
    return value != null;
  }

  Future<void> deleteToken() async {
    return _storage.delete(key: _tokenKey);
  }

  Future<void> deleteUserInfo() async {
    return _storage.delete(key: _userInfoKey);
  }

  Future<String?> getToken() async {
    return _storage.read(key: _tokenKey);
  }

  Future<dynamic> getUserInfo() async {
    return _storage.read(key: _userInfoKey);
  }

  Future<void> deleteAll() async {
    return _storage.deleteAll();
  }

}
