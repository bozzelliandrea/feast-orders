import 'package:auto_route/auto_route.dart';
import 'package:orders/categories/categories_page.dart';
import 'package:orders/categories/category_page.dart';
import 'package:orders/login/login_page.dart';
import 'package:orders/login/login_wrapper_page.dart';
import 'package:orders/login/register_page.dart';
import 'package:orders/orders/order_page.dart';
import 'package:orders/orders/orders_page.dart';
import 'package:orders/settings/printers_setup_page.dart';
import 'package:orders/settings/settings_page.dart';
import '../home.dart';

@MaterialAutoRouter(
  replaceInRouteName: 'Page,Route',
  routes: [
    AutoRoute(
        path: '/login',
        page: LoginWrapperPage,
        children: [
          AutoRoute(
            path: '',
            page: LoginPage,
          ),
          AutoRoute(
            path: 'register',
            page: RegisterPage,
          ),
        ]
    ),
    AutoRoute(path: '/', page: HomePage, children: [
      // AutoRoute(
      //   path: 'login',
      //   name: 'LoginRouter',
      //   page: EmptyRouterPage,
      //   children: [
      //     AutoRoute(
      //       path: '',
      //       page: LoginPage,
      //     ),
      //     AutoRoute(
      //       path: 'register',
      //       page: RegisterPage,
      //     ),
      //   ]
      // ),
      AutoRoute(
        path: 'explore',
        name: 'ExploreRouter',
        page: EmptyRouterPage,
        children: [
          AutoRoute(
            path: '',
            page: CategoriesPage,
          ),
          AutoRoute(
            path: ':categoryId',
            page: CategoryPage,
          ),
        ],
      ),
      AutoRoute(
        path: 'orders',
        name: 'OrdersRouter',
        page: EmptyRouterPage,
        children: [
          AutoRoute(
            path: '',
            page: OrdersPage,
          ),
          AutoRoute(
            path: ':orderId',
            page: OrderPage,
          )
        ]
      ),
      AutoRoute(
        path: 'settings',
        name: 'SettingsRouter',
        page: EmptyRouterPage,
        children: [
          AutoRoute(
            path: '',
            page: SettingsPage,
          ),
          AutoRoute(
            path: 'printers',
            page: PrintersSetupPage,
          ),
        ],
      ),
    ]),
  ],
)
class $AppRouter {}