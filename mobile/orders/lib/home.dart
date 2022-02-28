import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:orders/routes/router.gr.dart';
import 'package:salomon_bottom_bar/salomon_bottom_bar.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AutoTabsScaffold(
      appBarBuilder: (_, tabsRouter) => AppBar(
        backgroundColor: Colors.deepOrange,
        title: const Text('Feast Bear Orders'),
        centerTitle: true,
        leading: const AutoBackButton(),
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
    // return AutoTabsRouter(
    //   // list of your tab routes
    //   // routes used here must be declared as children
    //   // routes of /dashboard
    //   routes: const [
    //     ExploreRouter(),
    //     OrdersRouter(),
    //     SettingsRouter()
    //   ],
    //   builder: (context, child, animation) {
    //     // obtain the scoped TabsRouter controller using context
    //     final tabsRouter = AutoTabsRouter.of(context);
    //     // Here we're building our Scaffold inside of AutoTabsRouter
    //     // to access the tabsRouter controller provided in this context
    //     //
    //     //alterntivly you could use a global key
    //     return Scaffold(
    //         appBar: AppBar(
    //           backgroundColor: Colors.deepOrange,
    //           title: const Text('Feast Bear Orders'),
    //           centerTitle: true,
    //           leading: const AutoBackButton(),
    //         ),
    //         body: FadeTransition(
    //           opacity: animation,
    //           // the passed child is techinaclly our animated selected-tab page
    //           child: child,
    //         ),
    //         bottomNavigationBar: BottomNavigationBar(
    //           currentIndex: tabsRouter.activeIndex,
    //           selectedItemColor: Colors.deepOrange,
    //           onTap: (index) {
    //             // here we switch between tabs
    //             tabsRouter.setActiveIndex(index);
    //           },
    //           items: const [
    //             BottomNavigationBarItem(
    //               icon: Icon(Icons.search),
    //               label: 'Explore',
    //             ),
    //             BottomNavigationBarItem(
    //               icon: Icon(Icons.shopping_cart_outlined),
    //               label: 'Orders',
    //             ),
    //             BottomNavigationBarItem(
    //               icon: Icon(Icons.settings_outlined),
    //               label: 'Settings',
    //             ),
    //           ],
    //         ));
    //   },
    // );
  }
}
