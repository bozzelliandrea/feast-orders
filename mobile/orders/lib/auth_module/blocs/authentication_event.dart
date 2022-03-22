import 'package:equatable/equatable.dart';

abstract class AuthenticationEvent extends Equatable {}

class AppStarted extends AuthenticationEvent {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class LogIn extends AuthenticationEvent {
  final String username;
  final String password;

  @override
  List<Object?> get props => [username];

  LogIn(this.username, this.password);
}

class LogOut extends AuthenticationEvent {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}