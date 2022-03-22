import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:orders/auth_module/blocs/authentication_bloc.dart';
import 'package:orders/auth_module/blocs/authentication_event.dart';
import 'package:orders/routes/router.gr.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';

class HomePage extends StatelessWidget {
  HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AutoTabsScaffold(
      appBarBuilder: (_, tabsRouter) => AppBar(
        backgroundColor: Colors.deepOrange,
        title: const Text('Feast Bear Orders'),
        centerTitle: true,
        leading: const AutoBackButton(),
        actions: <Widget>[
          IconButton(
          icon: const Icon(Icons.logout),
          tooltip: 'Logout',
          onPressed: () {
            BlocProvider.of<AuthenticationBloc>(context).add(LogOut());
          }),
        ],
      ),
      routes: const [
        ExploreRouter(),
        OrdersRouter(),
        SettingsRouter()
      ],
      bottomNavigationBuilder: (_, tabsRouter) {
        return SalomonBottomBar(
          currentIndex: tabsRouter.activeIndex,
          onTap: tabsRouter.setActiveIndex,
          items: [
            SalomonBottomBarItem(
              selectedColor: Colors.deepOrange,
              icon: const Icon(
                Icons.search,
                size: 30,
              ),
              title: const Text('Explore'),
            ),
            SalomonBottomBarItem(
              selectedColor: Colors.deepOrange,
              icon: const Icon(
                Icons.shopping_cart_outlined,
                size: 30,
              ),
              title: const Text('Orders'),
            ),
            SalomonBottomBarItem(
              selectedColor: Colors.deepOrange,
              icon: const Icon(
                Icons.settings_outlined,
                size: 30,
              ),
              title: const Text('Settings'),
            ),
          ],
        );
      },
    );
  }
}
