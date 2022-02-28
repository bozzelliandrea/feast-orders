// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// AutoRouteGenerator
// **************************************************************************

import 'package:auto_route/auto_route.dart' as _i2;
import 'package:flutter/material.dart' as _i11;

import '../categories/categories_page.dart' as _i5;
import '../categories/category_page.dart' as _i6;
import '../home.dart' as _i1;
import '../login/login_page.dart' as _i3;
import '../login/register_page.dart' as _i4;
import '../orders/order_page.dart' as _i8;
import '../orders/orders_page.dart' as _i7;
import '../settings/printers_setup_page.dart' as _i10;
import '../settings/settings_page.dart' as _i9;

class AppRouter extends _i2.RootStackRouter {
  AppRouter([_i11.GlobalKey<_i11.NavigatorState>? navigatorKey])
      : super(navigatorKey);

  @override
  final Map<String, _i2.PageFactory> pagesMap = {
    HomeRoute.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i1.HomePage());
    },
    LoginRouter.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i2.EmptyRouterPage());
    },
    ExploreRouter.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i2.EmptyRouterPage());
    },
    OrdersRouter.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i2.EmptyRouterPage());
    },
    SettingsRouter.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i2.EmptyRouterPage());
    },
    LoginRoute.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i3.LoginPage());
    },
    RegisterRoute.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i4.RegisterPage());
    },
    CategoriesRoute.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i5.CategoriesPage());
    },
    CategoryRoute.name: (routeData) {
      final pathParams = routeData.pathParams;
      final args = routeData.argsAs<CategoryRouteArgs>(
          orElse: () =>
              CategoryRouteArgs(categoryId: pathParams.getInt('categoryId')));
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData,
          child: _i6.CategoryPage(key: args.key, categoryId: args.categoryId));
    },
    OrdersRoute.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i7.OrdersPage());
    },
    OrderRoute.name: (routeData) {
      final pathParams = routeData.pathParams;
      final args = routeData.argsAs<OrderRouteArgs>(
          orElse: () => OrderRouteArgs(orderId: pathParams.getInt('orderId')));
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData,
          child: _i8.OrderPage(key: args.key, orderId: args.orderId));
    },
    SettingsRoute.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i9.SettingsPage());
    },
    PrintersSetupRoute.name: (routeData) {
      return _i2.MaterialPageX<dynamic>(
          routeData: routeData, child: const _i10.PrintersSetupPage());
    }
  };

  @override
  List<_i2.RouteConfig> get routes => [
        _i2.RouteConfig(HomeRoute.name, path: '/', children: [
          _i2.RouteConfig(LoginRouter.name, path: 'login', children: [
            _i2.RouteConfig(LoginRoute.name, path: ''),
            _i2.RouteConfig(RegisterRoute.name, path: 'register')
          ]),
          _i2.RouteConfig(ExploreRouter.name, path: 'explore', children: [
            _i2.RouteConfig(CategoriesRoute.name, path: ''),
            _i2.RouteConfig(CategoryRoute.name, path: ':categoryId')
          ]),
          _i2.RouteConfig(OrdersRouter.name, path: 'orders', children: [
            _i2.RouteConfig(OrdersRoute.name, path: ''),
            _i2.RouteConfig(OrderRoute.name, path: ':orderId')
          ]),
          _i2.RouteConfig(SettingsRouter.name, path: 'settings', children: [
            _i2.RouteConfig(SettingsRoute.name, path: ''),
            _i2.RouteConfig(PrintersSetupRoute.name, path: 'printers')
          ])
        ])
      ];
}

/// generated route for [_i1.HomePage]
class HomeRoute extends _i2.PageRouteInfo<void> {
  const HomeRoute({List<_i2.PageRouteInfo>? children})
      : super(name, path: '/', initialChildren: children);

  static const String name = 'HomeRoute';
}

/// generated route for [_i2.EmptyRouterPage]
class LoginRouter extends _i2.PageRouteInfo<void> {
  const LoginRouter({List<_i2.PageRouteInfo>? children})
      : super(name, path: 'login', initialChildren: children);

  static const String name = 'LoginRouter';
}

/// generated route for [_i2.EmptyRouterPage]
class ExploreRouter extends _i2.PageRouteInfo<void> {
  const ExploreRouter({List<_i2.PageRouteInfo>? children})
      : super(name, path: 'explore', initialChildren: children);

  static const String name = 'ExploreRouter';
}

/// generated route for [_i2.EmptyRouterPage]
class OrdersRouter extends _i2.PageRouteInfo<void> {
  const OrdersRouter({List<_i2.PageRouteInfo>? children})
      : super(name, path: 'orders', initialChildren: children);

  static const String name = 'OrdersRouter';
}

/// generated route for [_i2.EmptyRouterPage]
class SettingsRouter extends _i2.PageRouteInfo<void> {
  const SettingsRouter({List<_i2.PageRouteInfo>? children})
      : super(name, path: 'settings', initialChildren: children);

  static const String name = 'SettingsRouter';
}

/// generated route for [_i3.LoginPage]
class LoginRoute extends _i2.PageRouteInfo<void> {
  const LoginRoute() : super(name, path: '');

  static const String name = 'LoginRoute';
}

/// generated route for [_i4.RegisterPage]
class RegisterRoute extends _i2.PageRouteInfo<void> {
  const RegisterRoute() : super(name, path: 'register');

  static const String name = 'RegisterRoute';
}

/// generated route for [_i5.CategoriesPage]
class CategoriesRoute extends _i2.PageRouteInfo<void> {
  const CategoriesRoute() : super(name, path: '');

  static const String name = 'CategoriesRoute';
}

/// generated route for [_i6.CategoryPage]
class CategoryRoute extends _i2.PageRouteInfo<CategoryRouteArgs> {
  CategoryRoute({_i11.Key? key, required int categoryId})
      : super(name,
            path: ':categoryId',
            args: CategoryRouteArgs(key: key, categoryId: categoryId),
            rawPathParams: {'categoryId': categoryId});

  static const String name = 'CategoryRoute';
}

class CategoryRouteArgs {
  const CategoryRouteArgs({this.key, required this.categoryId});

  final _i11.Key? key;

  final int categoryId;
}

/// generated route for [_i7.OrdersPage]
class OrdersRoute extends _i2.PageRouteInfo<void> {
  const OrdersRoute() : super(name, path: '');

  static const String name = 'OrdersRoute';
}

/// generated route for [_i8.OrderPage]
class OrderRoute extends _i2.PageRouteInfo<OrderRouteArgs> {
  OrderRoute({_i11.Key? key, required int orderId})
      : super(name,
            path: ':orderId',
            args: OrderRouteArgs(key: key, orderId: orderId),
            rawPathParams: {'orderId': orderId});

  static const String name = 'OrderRoute';
}

class OrderRouteArgs {
  const OrderRouteArgs({this.key, required this.orderId});

  final _i11.Key? key;

  final int orderId;
}

/// generated route for [_i9.SettingsPage]
class SettingsRoute extends _i2.PageRouteInfo<void> {
  const SettingsRoute() : super(name, path: '');

  static const String name = 'SettingsRoute';
}

/// generated route for [_i10.PrintersSetupPage]
class PrintersSetupRoute extends _i2.PageRouteInfo<void> {
  const PrintersSetupRoute() : super(name, path: 'printers');

  static const String name = 'PrintersSetupRoute';
}
