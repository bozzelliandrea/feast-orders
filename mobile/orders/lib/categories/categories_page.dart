import 'package:commons/commons.dart';
import 'package:flutter/material.dart';
import 'package:auto_route/auto_route.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:orders/blocs/authentication_bloc.dart';
import 'package:orders/blocs/authentication_state.dart';
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
            name: 'Primi',
            description: 'Contiene il menu dei primi',
            color: Colors.orangeAccent,
            onTap: () => context.router.push(
              CategoryRoute(
                categoryId: 1,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
