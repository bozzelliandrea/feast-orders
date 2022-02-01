import 'package:flutter/material.dart';
import 'package:plates/app_theme.dart';
import 'package:provider/provider.dart';

import 'navigation/app_router.dart';
import 'state/app_state_manager.dart';

void main() {
  runApp(
      const FeastBearPlates()
  );
}

class FeastBearPlates extends StatefulWidget {
  const FeastBearPlates({Key? key}) : super(key: key);

  @override
  _FeastBearPlatesState createState() => _FeastBearPlatesState();
}

class _FeastBearPlatesState extends State<FeastBearPlates> {

  final _appStateManager = AppStateManager();
  late AppRouter _appRouter;

  @override
  void initState() {
    super.initState();
    _appRouter = AppRouter(
      appStateManager: _appStateManager
    );
  }

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (context) => _appStateManager,
        )
      ],
      child: MaterialApp(
        title: 'FeastBear Plates',
        debugShowCheckedModeBanner: false,
        theme: AppTheme.light(),
        home: Router(
          routerDelegate: _appRouter,
          backButtonDispatcher: RootBackButtonDispatcher()
        )
      ),
    );
  }

}
