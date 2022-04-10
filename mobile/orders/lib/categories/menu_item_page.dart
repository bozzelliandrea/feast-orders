import 'package:auto_route/annotations.dart';
import 'package:flutter/material.dart';

class MenuItemPage extends StatelessWidget {
  final int menuItemId;

  const MenuItemPage({Key? key, @PathParam() required this.menuItemId}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text('Menu item page $menuItemId'),
    );
  }
}
