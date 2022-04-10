import 'package:auto_route/auto_route.dart';
import 'package:orders/categories/categories_page.dart';
import 'package:orders/categories/category_page.dart';
import 'package:orders/auth_module/screens/login_page.dart';
import 'package:orders/auth_module/screens/login_wrapper_page.dart';
import 'package:orders/auth_module/screens/change_password_page.dart';
import 'package:orders/categories/menu_item_page.dart';
import 'package:orders/orders/order_page.dart';
import 'package:orders/orders/orders_page.dart';
import 'package:orders/settings/printers_setup_page.dart';
import 'package:orders/settings/settings_page.dart';
import 'package:orders/settings/users_setup_page.dart';
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
          path: 'changepassword',
          page: ChangePasswordPage,
        ),
      ]
    ),
    AutoRoute(path: '/', page: HomePage, children: [
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
            page: CategoryPage
          ),
          AutoRoute(
            path: 'menuitems/:menuItemId',
            page: MenuItemPage,
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
          AutoRoute(
            path: 'users',
            page: UsersSetupPage,
          ),
        ],
      ),
    ]),
  ],
)
class $AppRouter {}