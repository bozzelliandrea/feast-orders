import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:orders/routes/router.gr.dart';

class LoginWrapperPage extends StatefulWidget {
  final Function(bool isLoggedIn) onLogin;

  const LoginWrapperPage({
    Key? key,
    required this.onLogin
  }) : super(key: key);

  @override
  _LoginWrapperPageState createState() => _LoginWrapperPageState();
}

class _LoginWrapperPageState extends State<LoginWrapperPage> {
  String email = "";

  @override
  Widget build(context) => AutoRouter.declarative( // use AutoRouter.declarative
    routes: (_) {
      // Declaratively define your routes here
        return [LoginRoute()];
      }
        // if (email.isNotEmpty) PasswordRoute(onNext: (result) async {
        //   try {
        //     // validate the email and password
        //     await validateEmailAndPassword(email, result)
        //     widget.onLogin(true);
        //   } catch (e) {
        //     // do something with the error
        //   }
        // }),
  );
}