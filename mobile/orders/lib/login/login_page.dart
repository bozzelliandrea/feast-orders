import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:orders/blocs/authentication_bloc.dart';
import 'package:orders/blocs/authentication_event.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          children: [
            Text('Login Page'),
            ElevatedButton(
              onPressed: () => BlocProvider.of<AuthenticationBloc>(context).add(LogIn()),
              child: Text('LOGIN'),
            ),
          ],
        ),
      ),
    );
  }
}
