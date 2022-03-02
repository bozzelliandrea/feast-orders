import 'package:flutter_bloc/flutter_bloc.dart';

import 'authentication_event.dart';
import 'authentication_state.dart';

class AuthenticationBloc extends Bloc<AuthenticationEvent, AuthenticationState> {

  AuthenticationBloc() : super(AuthenticationUnauthenticated()) {

    on<AppStarted>((event, emit) => emit(AuthenticationUninitialized()));

    on<LogIn>((event, emit) async {
      emit(LoggingIn());
      await Future.delayed(Duration(seconds: 3));
      emit(LoggedIn());
    });

    on<LogOut>((event, emit) async {
      await Future.delayed(Duration(seconds: 3));
      emit(AuthenticationUninitialized());
    });

  }

}

