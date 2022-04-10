import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:orders/auth_module/blocs/authentication_bloc.dart';
import 'package:orders/auth_module/blocs/authentication_state.dart';
import 'package:orders/routes/router.gr.dart';

import 'category_item.dart';

class CategoriesPage extends StatelessWidget {
  const CategoriesPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return
      Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          BlocBuilder<AuthenticationBloc, AuthenticationState>(
            builder: (context, state) {
              if (state is LoggedIn) {
                String? username = state.username;
                String? email = state.email;
                return Text('Welcome $username ($email)');
              } else {
                return const Text('No logged user');
              }
            }
          ),
          const Text('Categories Page'),
          CategoryItem(
            name: 'Panini',
            description: 'Contiene il menu dei panini',
            color: Colors.orangeAccent,
            onTap: () => context.router.push(
              CategoryRoute(
                categoryId: 1, // fixme
              ),
            ),
          ),
        ],
      ),
    );
  }
}
