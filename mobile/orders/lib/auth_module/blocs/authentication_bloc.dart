import 'package:commons/commons.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:orders/auth_module/data/auth_base_service.dart';
import 'package:orders/auth_module/models/auth.dart';
import 'package:orders/auth_module/models/auth_response.dart';

import 'authentication_event.dart';
import 'authentication_state.dart';

class AuthenticationBloc extends Bloc<AuthenticationEvent, AuthenticationState> {

  final AuthBaseService authService;
  final AuthSessionStorage _authSessionStorage = AuthSessionStorage();

  // todo: adjust authentication flow

  AuthenticationBloc(this.authService) : super(AuthenticationUnauthenticated()) {

    on<AppStarted>((event, emit) => emit(AuthenticationUninitialized()));

    on<LogIn>((event, emit) async {
      AuthResponse auth = await authService.login(
        Auth(event.username, event.password, '')
      );

      emit(LoggedIn(auth.username, auth.email));
    });

    on<LogOut>((event, emit) async {
      await authService.logout();
      emit(AuthenticationUninitialized());
    });

  }

}

