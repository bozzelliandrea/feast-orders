import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:orders/blocs/authentication_bloc.dart';
import 'package:orders/blocs/authentication_event.dart';
import 'package:orders/configuration.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          children: [
            const Text('Login Page'),
            ElevatedButton(
              onPressed: () => BlocProvider.of<AuthenticationBloc>(context).add(
                LogIn(Configuration.userInfo.username!, Configuration.userInfo.password!)
              ),
              child: const Text('LOGIN'),
            ),
          ],
        ),
      ),
    );
  }
}
