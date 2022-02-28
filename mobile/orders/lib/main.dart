import 'package:flutter/material.dart';
import 'package:orders/routes/router.gr.dart';

void main() {
  runApp(FeastOrdersApp());
}

class FeastOrdersApp extends StatelessWidget {
  FeastOrdersApp({Key? key}) : super(key: key);
  final _appRouter = AppRouter();

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      debugShowCheckedModeBanner: false,
      title: 'Feast Bear Orders',
      routerDelegate: _appRouter.delegate(),
      routeInformationParser: _appRouter.defaultRouteParser(),
    );
  }
}
