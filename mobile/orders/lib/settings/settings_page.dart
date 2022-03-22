import 'package:flutter/material.dart';
import 'package:auto_route/auto_route.dart';
import 'package:orders/routes/router.gr.dart';
import 'package:orders/settings/settings_item.dart';

class SettingsPage extends StatelessWidget {
  const SettingsPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Text('Settings Page'),
        SettingsItem(
          name: 'Printers Setup',
          onTap: () => context.router.push(
            const PrintersSetupRoute(),
          ),
        ),
        SettingsItem(
          name: 'Users Setup',
          onTap: () => context.router.push(
            const UsersSetupRoute(),
          ),
        ),
      ],
    );
  }
}
