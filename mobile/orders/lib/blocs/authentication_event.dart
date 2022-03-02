import 'package:equatable/equatable.dart';

abstract class AuthenticationEvent extends Equatable {}

class AppStarted extends AuthenticationEvent {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class LogIn extends AuthenticationEvent {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class LogOut extends AuthenticationEvent {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}