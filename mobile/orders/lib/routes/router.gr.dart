// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// AutoRouteGenerator
// **************************************************************************

import 'package:auto_route/auto_route.dart' as _i5;
import 'package:flutter/material.dart' as _i14;

import '../auth_module/screens/change_password_page.dart' as _i4;
import '../auth_module/screens/login_page.dart' as _i3;
import '../auth_module/screens/login_wrapper_page.dart' as _i1;
import '../categories/categories_page.dart' as _i6;
import '../categories/category_page.dart' as _i7;
import '../categories/menu_item_page.dart' as _i8;
import '../home.dart' as _i2;
import '../orders/order_page.dart' as _i10;
import '../orders/orders_page.dart' as _i9;
import '../settings/printers_setup_page.dart' as _i12;
import '../settings/settings_page.dart' as _i11;
import '../settings/users_setup_page.dart' as _i13;

class AppRouter extends _i5.RootStackRouter {
  AppRouter([_i14.GlobalKey<_i14.NavigatorState>? navigatorKey])
      : super(navigatorKey);

  @override
  final Map<String, _i5.PageFactory> pagesMap = {
    LoginWrapperRoute.name: (routeData) {
      final args = routeData.argsAs<LoginWrapperRouteArgs>();
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData,
          child: _i1.LoginWrapperPage(key: args.key, onLogin: args.onLogin));
    },
    HomeRoute.name: (routeData) {
      final args =
          routeData.argsAs<HomeRouteArgs>(orElse: () => const HomeRouteArgs());
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: _i2.HomePage(key: args.key));
    },
    LoginRoute.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i3.LoginPage());
    },
    ChangePasswordRoute.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i4.ChangePasswordPage());
    },
    ExploreRouter.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i5.EmptyRouterPage());
    },
    OrdersRouter.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i5.EmptyRouterPage());
    },
    SettingsRouter.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i5.EmptyRouterPage());
    },
    CategoriesRoute.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i6.CategoriesPage());
    },
    CategoryRoute.name: (routeData) {
      final pathParams = routeData.pathParams;
      final args = routeData.argsAs<CategoryRouteArgs>(
          orElse: () =>
              CategoryRouteArgs(categoryId: pathParams.getInt('categoryId')));
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData,
          child: _i7.CategoryPage(key: args.key, categoryId: args.categoryId));
    },
    MenuItemRoute.name: (routeData) {
      final pathParams = routeData.pathParams;
      final args = routeData.argsAs<MenuItemRouteArgs>(
          orElse: () =>
              MenuItemRouteArgs(menuItemId: pathParams.getInt('menuItemId')));
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData,
          child: _i8.MenuItemPage(key: args.key, menuItemId: args.menuItemId));
    },
    OrdersRoute.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i9.OrdersPage());
    },
    OrderRoute.name: (routeData) {
      final pathParams = routeData.pathParams;
      final args = routeData.argsAs<OrderRouteArgs>(
          orElse: () => OrderRouteArgs(orderId: pathParams.getInt('orderId')));
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData,
          child: _i10.OrderPage(key: args.key, orderId: args.orderId));
    },
    SettingsRoute.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i11.SettingsPage());
    },
    PrintersSetupRoute.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i12.PrintersSetupPage());
    },
    UsersSetupRoute.name: (routeData) {
      return _i5.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i13.UsersSetupPage());
    }
  };

  @override
  List<_i5.RouteConfig> get routes => [
        _i5.RouteConfig(LoginWrapperRoute.name, path: '/login', children: [
          _i5.RouteConfig(LoginRoute.name, path: ''),
          _i5.RouteConfig(ChangePasswordRoute.name, path: 'changepassword')
        ]),
        _i5.RouteConfig(HomeRoute.name, path: '/', children: [
          _i5.RouteConfig(ExploreRouter.name, path: 'explore', children: [
            _i5.RouteConfig(CategoriesRoute.name, path: ''),
            _i5.RouteConfig(CategoryRoute.name, path: ':categoryId'),
            _i5.RouteConfig(MenuItemRoute.name, path: 'menuitems/:menuItemId')
          ]),
          _i5.RouteConfig(OrdersRouter.name, path: 'orders', children: [
            _i5.RouteConfig(OrdersRoute.name, path: ''),
            _i5.RouteConfig(OrderRoute.name, path: ':orderId')
          ]),
          _i5.RouteConfig(SettingsRouter.name, path: 'settings', children: [
            _i5.RouteConfig(SettingsRoute.name, path: ''),
            _i5.RouteConfig(PrintersSetupRoute.name, path: 'printers'),
            _i5.RouteConfig(UsersSetupRoute.name, path: 'users')
          ])
        ])
      ];
}

/// generated route for [_i1.LoginWrapperPage]
class LoginWrapperRoute extends _i5.PageRouteInfo<LoginWrapperRouteArgs> {
  LoginWrapperRoute(
      {_i14.Key? key,
      required dynamic Function(bool) onLogin,
      List<_i5.PageRouteInfo>? children})
      : super(name,
            path: '/login',
            args: LoginWrapperRouteArgs(key: key, onLogin: onLogin),
            initialChildren: children);

  static const String name = 'LoginWrapperRoute';
}

class LoginWrapperRouteArgs {
  const LoginWrapperRouteArgs({this.key, required this.onLogin});

  final _i14.Key? key;

  final dynamic Function(bool) onLogin;
}

/// generated route for [_i2.HomePage]
class HomeRoute extends _i5.PageRouteInfo<HomeRouteArgs> {
  HomeRoute({_i14.Key? key, List<_i5.PageRouteInfo>? children})
      : super(name,
            path: '/',
            args: HomeRouteArgs(key: key),
            initialChildren: children);

  static const String name = 'HomeRoute';
}

class HomeRouteArgs {
  const HomeRouteArgs({this.key});

  final _i14.Key? key;
}

/// generated route for [_i3.LoginPage]
class LoginRoute extends _i5.PageRouteInfo<void> {
  const LoginRoute() : super(name, path: '');

  static const String name = 'LoginRoute';
}

/// generated route for [_i4.ChangePasswordPage]
class ChangePasswordRoute extends _i5.PageRouteInfo<void> {
  const ChangePasswordRoute() : super(name, path: 'changepassword');

  static const String name = 'ChangePasswordRoute';
}

/// generated route for [_i5.EmptyRouterPage]
class ExploreRouter extends _i5.PageRouteInfo<void> {
  const ExploreRouter({List<_i5.PageRouteInfo>? children})
      : super(name, path: 'explore', initialChildren: children);

  static const String name = 'ExploreRouter';
}

/// generated route for [_i5.EmptyRouterPage]
class OrdersRouter extends _i5.PageRouteInfo<void> {
  const OrdersRouter({List<_i5.PageRouteInfo>? children})
      : super(name, path: 'orders', initialChildren: children);

  static const String name = 'OrdersRouter';
}

/// generated route for [_i5.EmptyRouterPage]
class SettingsRouter extends _i5.PageRouteInfo<void> {
  const SettingsRouter({List<_i5.PageRouteInfo>? children})
      : super(name, path: 'settings', initialChildren: children);

  static const String name = 'SettingsRouter';
}

/// generated route for [_i6.CategoriesPage]
class CategoriesRoute extends _i5.PageRouteInfo<void> {
  const CategoriesRoute() : super(name, path: '');

  static const String name = 'CategoriesRoute';
}

/// generated route for [_i7.CategoryPage]
class CategoryRoute extends _i5.PageRouteInfo<CategoryRouteArgs> {
  CategoryRoute({_i14.Key? key, required int categoryId})
      : super(name,
            path: ':categoryId',
            args: CategoryRouteArgs(key: key, categoryId: categoryId),
            rawPathParams: {'categoryId': categoryId});

  static const String name = 'CategoryRoute';
}

class CategoryRouteArgs {
  const CategoryRouteArgs({this.key, required this.categoryId});

  final _i14.Key? key;

  final int categoryId;
}

/// generated route for [_i8.MenuItemPage]
class MenuItemRoute extends _i5.PageRouteInfo<MenuItemRouteArgs> {
  MenuItemRoute({_i14.Key? key, required int menuItemId})
      : super(name,
            path: 'menuitems/:menuItemId',
            args: MenuItemRouteArgs(key: key, menuItemId: menuItemId),
            rawPathParams: {'menuItemId': menuItemId});

  static const String name = 'MenuItemRoute';
}

class MenuItemRouteArgs {
  const MenuItemRouteArgs({this.key, required this.menuItemId});

  final _i14.Key? key;

  final int menuItemId;
}

/// generated route for [_i9.OrdersPage]
class OrdersRoute extends _i5.PageRouteInfo<void> {
  const OrdersRoute() : super(name, path: '');

  static const String name = 'OrdersRoute';
}

/// generated route for [_i10.OrderPage]
class OrderRoute extends _i5.PageRouteInfo<OrderRouteArgs> {
  OrderRoute({_i14.Key? key, required int orderId})
      : super(name,
            path: ':orderId',
            args: OrderRouteArgs(key: key, orderId: orderId),
            rawPathParams: {'orderId': orderId});

  static const String name = 'OrderRoute';
}

class OrderRouteArgs {
  const OrderRouteArgs({this.key, required this.orderId});

  final _i14.Key? key;

  final int orderId;
}

/// generated route for [_i11.SettingsPage]
class SettingsRoute extends _i5.PageRouteInfo<void> {
  const SettingsRoute() : super(name, path: '');

  static const String name = 'SettingsRoute';
}

/// generated route for [_i12.PrintersSetupPage]
class PrintersSetupRoute extends _i5.PageRouteInfo<void> {
  const PrintersSetupRoute() : super(name, path: 'printers');

  static const String name = 'PrintersSetupRoute';
}

/// generated route for [_i13.UsersSetupPage]
class UsersSetupRoute extends _i5.PageRouteInfo<void> {
  const UsersSetupRoute() : super(name, path: 'users');

  static const String name = 'UsersSetupRoute';
}
