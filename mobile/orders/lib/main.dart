import 'package:auto_route/auto_route.dart';
import 'package:commons/commons.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:orders/blocs/authentication_bloc.dart';
import 'package:orders/blocs/authentication_event.dart';
import 'package:orders/configuration.dart';
import 'package:orders/repositories/repository_bundle.dart';
import 'package:orders/root_provider.dart';
import 'package:orders/routes/router.gr.dart';

import 'blocs/authentication_state.dart';

void main() {
  runApp(FeastOrdersApp());
}

class FeastOrdersApp extends StatelessWidget {

  FeastOrdersApp({Key? key}) : super(key: key);

  final _appRouter = AppRouter();
  final ApiService _apiService = ApiService.getInstance(Configuration.baseUrl);
  final AuthSessionStorage _authSessionStorage = AuthSessionStorage();

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return RootProvider(
      apiService: _apiService,
      authSessionStorage: _authSessionStorage,
      child: BlocProvider(
        create: (context) => AuthenticationBloc(
            RepositoryProvider.of<RepositoryBundle>(context).authService
        )..add(AppStarted()),
        child: BlocBuilder<AuthenticationBloc, AuthenticationState>(
          builder: (context, state) {
            return MaterialApp.router(
              debugShowCheckedModeBanner: false,
              title: 'Feast Bear Orders',
              theme: ThemeData(
                fontFamily: 'Montserrat',
              ),
              routerDelegate: AutoRouterDelegate.declarative(
                _appRouter,
                routes: (_) => [
                  // if the user is logged in, they may proceed to the main App
                  if (state is AuthenticationUninitialized)
                    LoginWrapperRoute(onLogin: (isLogin) {
                      return isLogin;
                    })
                  // if they are not logged in, bring them to the Login page
                  else
                    HomeRoute(),
                ],
              ),
              routeInformationParser: _appRouter.defaultRouteParser(),
            );
          },
        ),
      ),

    //   MaterialApp.router(
    //   debugShowCheckedModeBanner: false,
    //   title: 'Feast Bear Orders',
    //   routerDelegate: _appRouter.delegate(),
    //   routeInformationParser: _appRouter.defaultRouteParser(),
    );
  }
}
