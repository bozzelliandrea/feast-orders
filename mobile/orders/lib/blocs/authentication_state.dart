import 'package:equatable/equatable.dart';

abstract class AuthenticationState extends Equatable {
}

class AuthenticationAuthenticated extends AuthenticationState {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class AuthenticationUnauthenticated extends AuthenticationState {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class AuthenticationUninitialized extends AuthenticationState {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}

class LoggingIn extends AuthenticationState {
  @override
  // TODO: implement props
  List<Object?> get props => throw [];
}

class LoggedIn extends AuthenticationState {
  @override
  // TODO: implement props
  List<Object?> get props => [];
}


